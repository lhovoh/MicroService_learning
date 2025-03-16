from flask import Flask, request, jsonify, send_file
from wordcloud import WordCloud
import matplotlib.pyplot as plt
import pandas as pd
import io
import nacos

app = Flask(__name__)

# Nacos 配置
NACOS_SERVER = "localhost:8848"  # Nacos 服务器地址
NAMESPACE = "public"  # 命名空间，默认是 public
SERVICE_NAME = "python-service"  # 服务名称
IP = "127.0.0.1"  # 当前服务的 IP
PORT = 5000  # 当前服务的端口

# 初始化 Nacos 客户端
client = nacos.NacosClient(
    NACOS_SERVER,
    namespace=NAMESPACE,
    username="nacos",  # Nacos 用户名
    password="nacos"   # Nacos 密码
)

@app.route('/')
def home():
    return "Welcome to Python Service! Use /generate_wordcloud or /generate_bar_chart to generate images."

@app.route('/favicon.ico')
def favicon():
    return '', 204  # 返回空响应

@app.route('/generate_wordcloud', methods=['POST'])
def generate_wordcloud():
    data = request.json
    text = data.get('text', '')

    # 生成词云图
    wordcloud = WordCloud(width=800, height=400, background_color='white').generate(text)

    # 将图像保存到内存中
    buf = io.BytesIO()
    plt.figure(figsize=(10, 5))
    plt.imshow(wordcloud, interpolation='bilinear')
    plt.axis('off')
    plt.savefig(buf, format='png')
    buf.seek(0)

    # 返回图像
    return send_file(buf, mimetype='image/png')

@app.route('/generate_bar_chart', methods=['POST'])
def generate_bar_chart():
    data = request.json
    df = pd.DataFrame(data)

    # 生成柱状图
    plt.figure(figsize=(10, 5))
    df.plot(kind='bar')
    buf = io.BytesIO()
    plt.savefig(buf, format='png')
    buf.seek(0)

    # 返回图像
    return send_file(buf, mimetype='image/png')

def register_to_nacos():
    """将服务注册到 Nacos"""
    client.add_naming_instance(
        service_name=SERVICE_NAME,
        ip=IP,
        port=PORT,
        cluster_name="DEFAULT",
        weight=1.0,
        metadata={"version": "1.0.0"},
        enable=True,
        healthy=True,
        ephemeral=True,
    )
    print(f"Service {SERVICE_NAME} registered to Nacos.")

def deregister_from_nacos():
    """从 Nacos 注销服务"""
    client.remove_naming_instance(
        service_name=SERVICE_NAME,
        ip=IP,
        port=PORT,
        cluster_name="DEFAULT",
    )
    print(f"Service {SERVICE_NAME} deregistered from Nacos.")

if __name__ == '__main__':
    try:
        # 启动时注册服务
        register_to_nacos()
        # 运行 Flask 应用
        app.run(host=IP, port=PORT)
    except KeyboardInterrupt:
        # 停止时注销服务
        deregister_from_nacos()