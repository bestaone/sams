// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

export async function testLoadBalanc() {
  return request('/apisvc-mall/api/basic/testLoadBalanc', {
    method: 'GET'
  });
}

export async function testSentinel() {
  return request('/apisvc-mall/api/basic/testSentinel', {
    method: 'GET'
  });
}

export async function testSeata() {
  return request('/apisvc-mall/api/basic/testSeata', {
    method: 'GET'
  });
}
