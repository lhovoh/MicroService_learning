const axios = require('axios');
const { NacosNamingClient, NacosConfigClient } = require('nacos');

// Nacos 配置
const nacosConfig = {
    serverAddr: '127.0.0.1:8848',
    namespace: '', // 如果有命名空间，填写命名空间 ID
    username: 'nacos', // 如果有认证
    password: 'nacos', // 如果有认证
};

// 初始化 Nacos 命名服务客户端
const namingClient = new NacosNamingClient(nacosConfig);

// 初始化 Nacos 配置客户端
const configClient = new NacosConfigClient(nacosConfig);

// 获取服务实例
async function getServiceInstance(serviceName) {
    try {
        const instances = await namingClient.getAllInstances(serviceName);
        if (instances && instances.length > 0) {
            return instances[0]; // 返回第一个实例
        }
        throw new Error('No instances found');
    } catch (err) {
        console.error('Failed to get service instance:', err);
        return null;
    }
}

// 调用 example-service 的 /hello 接口
async function callExampleService() {
    const serviceName = 'example-service'; // 目标服务名称
    const instance = await getServiceInstance(serviceName);

    if (instance) {
        const url = `http://${instance.ip}:${instance.port}/hello`;
        try {
            const response = await axios.get(url);
            console.log('Response from example-service:', response.data);
        } catch (err) {
            console.error('Failed to call example-service:', err);
        }
    } else {
        console.error('No instance available for service:', serviceName);
    }
}

// 从 Nacos 获取配置
async function getConfig() {
    const dataId = 'test';
    const group = 'DEFAULT_GROUP';

    try {
        const content = await configClient.getConfig({ dataId, group });
        console.log('Config content:', content);
    } catch (err) {
        console.error('Failed to get config:', err);
    }
}

// 启动 user-service
(async () => {
    console.log('Starting user-service...');

    // 调用 example-service
    await callExampleService();

    // 获取配置
    await getConfig();
})();
