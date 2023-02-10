import { PageContainer } from '@ant-design/pro-components';
import { useModel } from '@umijs/max';
import {Button, Card, message, theme} from 'antd';
import React from 'react';
import {testLoadBalanc, testSeata, testSentinel} from "@/services/ant-design-pro/test";

/**
 * 每个单独的卡片，为了复用样式抽成了组件
 * @param param0
 * @returns
 */
const InfoCard: React.FC<{
  title: string;
  index: number;
  desc: string;
  href: string;
  testCallback?: any;
}> = ({ title, href, index, desc, testCallback }) => {
  const { useToken } = theme;

  const { token } = useToken();

  return (
    <div
      style={{
        backgroundColor: token.colorBgContainer,
        boxShadow: token.boxShadow,
        borderRadius: '8px',
        fontSize: '14px',
        color: token.colorTextSecondary,
        lineHeight: '22px',
        padding: '16px 19px',
        minWidth: '220px',
        flex: 1,
      }}
    >
      <div
        style={{
          display: 'flex',
          gap: '4px',
          alignItems: 'center',
        }}
      >
        <div
          style={{
            width: 48,
            height: 48,
            lineHeight: '22px',
            backgroundSize: '100%',
            textAlign: 'center',
            padding: '8px 16px 16px 12px',
            color: '#FFF',
            fontWeight: 'bold',
            backgroundImage:
              "url('https://gw.alipayobjects.com/zos/bmw-prod/daaf8d50-8e6d-4251-905d-676a24ddfa12.svg')",
          }}
        >
          {index}
        </div>
        <div
          style={{
            fontSize: '16px',
            color: token.colorText,
            paddingBottom: 8,
          }}
        >
          {title}
        </div>
      </div>
      <div
        style={{
          fontSize: '14px',
          color: token.colorTextSecondary,
          textAlign: 'justify',
          lineHeight: '22px',
          marginBottom: 8,
        }}
      >
        {desc}
      </div>
      <Button type="primary" onClick={testCallback}>
        测试
      </Button>
    </div>
  );
};

const Welcome: React.FC = () => {
  const { token } = theme.useToken();
  const { initialState } = useModel('@@initialState');
  return (
    <PageContainer>
      <Card
        style={{
          borderRadius: 8,
        }}
        bodyStyle={{
          backgroundImage:
            initialState?.settings?.navTheme === 'realDark'
              ? 'background-image: linear-gradient(75deg, #1A1B1F 0%, #191C1F 100%)'
              : 'background-image: linear-gradient(75deg, #FBFDFF 0%, #F5F7FF 100%)',
        }}
      >
        <div
          style={{
            backgroundPosition: '100% -30%',
            backgroundRepeat: 'no-repeat',
            backgroundSize: '274px auto',
            backgroundImage:
              "url('https://gw.alipayobjects.com/mdn/rms_a9745b/afts/img/A*BuFmQqsB2iAAAAAAAAAAAAAAARQnAQ')",
          }}
        >
          <div
            style={{
              fontSize: '20px',
              color: token.colorTextHeading,
            }}
          >
            欢迎使用 Sams System
          </div>
          <p
            style={{
              fontSize: '14px',
              color: token.colorTextSecondary,
              lineHeight: '22px',
              marginTop: 16,
              marginBottom: 32,
            }}
          >
            Sams System (Springcloud Alibaba Micro Service) 是一个基于 SpringCloud Alibaba 搭建的微服务框架，本框架旨在帮助大家学习以及在搭建微服务系统时提供参考。
          </p>
          <div
            style={{
              display: 'flex',
              flexWrap: 'wrap',
              gap: 16,
            }}
          >
            <InfoCard
              index={1}
              href="https://umijs.org/docs/introduce/introduce"
              title="负载均衡"
              desc="starter-route模块中实现了路由控制，随机的比例可以通过nacos服务器列表中进行配置（nacos.weight），在这里点击测试按钮会返回当前响应的服务器参数（demo服务器，只部署了单节点，所以返回结果只有一个）。"
              testCallback={async () => {
                const resp = await testLoadBalanc();
                const str = JSON.stringify(resp);
                message.success(str);
              }}
            />
            <InfoCard
              index={2}
              title="熔断降级"
              href="https://ant.design"
              desc="Sentinel支持了限流、熔断、降级功能，默认情况下不做任何限制，需要到控制台中对资源进行配置。先访问两次接口，然后在控制台中找到对应资源，配置限流：单机阈值=1，配置熔断：异常数=1，熔断时长=5s，最小请求数=1。然后快速多次点击按钮进行测试。"
              testCallback={async () => {
                const resp = await testSentinel();
                const str = JSON.stringify(resp);
                message.success(str);
              }}
            />
            <InfoCard
              index={3}
              title="分布式事务"
              href="https://procomponents.ant.design"
              desc="使用Seata管理分布式事务，点击按钮会同时往user表、corp表中插入一条数据，之后接口模拟抛出异常，Seata捕获到异常后会对user表、corp表进行回滚。"
              testCallback={async () => {
                const resp = await testSeata();
                const str = JSON.stringify(resp);
                message.success(str);
              }}
            />
          </div>
        </div>
      </Card>
    </PageContainer>
  );
};

export default Welcome;
