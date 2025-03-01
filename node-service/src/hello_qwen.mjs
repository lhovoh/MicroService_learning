import express from "express";
import { NacosNamingClient } from "nacos";
import mysql from "mysql2/promise";

// 创建 Express 应用
const app = express();
const port = 8086;

// 创建 Nacos 客户端
const client = new NacosNamingClient({
    serverList: "127.0.0.1:8848", // Nacos 服务地址
    namespace: "public", // 命名空间，如果有
    username: "nacos", // 用户名，可选
    password: "nacos", // 密码，可选
    logger: console, // 日志输出
});

// 创建 MySQL 连接池
const pool = mysql.createPool({
    host: "localhost",
    user: "root",
    password: "2003098",
    database: "testdb",
    waitForConnections: true,
    connectionLimit: 10,
    queueLimit: 0,
});

// 允许解析 JSON 请求体
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// 创建一个接口，接收用户输入并返回用户输入的内容
app.get("/node", (req, res) => {
    const userInput = req.query.user; // 获取导航栏输入的内容
    if (!userInput) {
        return res.status(400).send("No input provided");
    }
    res.send(`${userInput},nodejs欢迎你`);
});

// 查询数据
app.get("/users", async (req, res) => {
    try {
        const [rows] = await pool.query("SELECT * FROM users");
        res.json(rows);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// 插入数据
app.post("/users", async (req, res) => {
    const { name, email } = req.body;
    if (!name || !email) {
        return res.status(400).json({ error: "Name and email are required" });
    }

    try {
        const [result] = await pool.query("INSERT INTO users (name, email) VALUES (?, ?)", [name, email]);
        res.status(201).json({ id: result.insertId, name, email });
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// 更新数据
app.put("/users/:id", async (req, res) => {
    const { id } = req.params;
    const { name, email } = req.body;
    if (!name || !email) {
        return res.status(400).json({ error: "Name and email are required" });
    }

    try {
        const [result] = await pool.query("UPDATE users SET name = ?, email = ? WHERE id = ?", [name, email, id]);
        if (result.affectedRows === 0) {
            return res.status(404).json({ error: "User not found" });
        }
        res.json({ id, name, email });
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// 删除数据
app.delete("/users/:id", async (req, res) => {
    const { id } = req.params;

    try {
        const [result] = await pool.query("DELETE FROM users WHERE id = ?", [id]);
        if (result.affectedRows === 0) {
            return res.status(404).json({ error: "User not found" });
        }
        res.status(204).send();
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
});

// 启动 HTTP 服务
app.listen(port, async () => {
    console.log(`[Node.js] 服务已启动，监听端口: ${port}`);

    try {
        await client.ready(); // 等待 Nacos 客户端初始化完成
        const serviceName = "node-service"; // 服务名称
        const ip = "127.0.0.1"; // 服务实例 IP，实际部署时可以使用动态获取

        // 注册服务实例到 Nacos
        await client.registerInstance(serviceName, {
            ip,
            port,
            metadata: {
                componentName: "node-app",
                address: `${ip}:${port}`,
            },
        });

        console.log(`[Nacos] 服务实例注册成功: ${ip}:${port}`);
    } catch (err) {
        console.error("[Nacos] 服务实例注册失败:", err);
    }
});
