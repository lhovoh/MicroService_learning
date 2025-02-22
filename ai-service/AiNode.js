const express = require('express');
const { NacosConfigClient } = require('nacos'); // 确保导入正确的库和类

// 创建 Express 应用
const app = express();
const port = 8085;

// Nacos 配置客户端初始化
const configClient = new NacosConfigClient({
    serverAddr: '127.0.0.1:8848',
    dataId: 'test',
    group: 'DEFAULT_GROUP',
    username: 'nacos', // 如果需要认证，请提供用户名
    password: 'nacos', // 如果需要认证，请提供密码
});

// 获取配置
(async () => {
    try {
        const content = await configClient.getConfig(); // 不需要传递 dataId 和 group
        console.log('getConfig = ', content);
    } catch (err) {
        console.error('Failed to get config:', err);
    }
})();

// 监听配置变化
configClient.subscribe({
    dataId: 'test',
    group: 'DEFAULT_GROUP',
}, content => {
    console.log('Config changed:', content);
});

// 发布配置
(async () => {
    try {
        const result = await configClient.publishConfig({
            dataId: 'test',
            group: 'DEFAULT_GROUP',
            content: '测试',
        });
        console.log('publishConfig result:', result);
    } catch (err) {
        console.error('Failed to publish config:', err);
    }
})();

// 定义一个简单的接口
app.get('/hello', (req, res) => {
    res.json({ message: 'Hello from Node.js service!' });
});

// 启动服务
app.listen(port, () => {
    console.log(`Node.js service is running on http://localhost:${port}`);
});
