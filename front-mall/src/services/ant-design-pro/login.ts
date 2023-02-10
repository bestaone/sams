// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';

/** 发送验证码 POST /api/login/captcha */
export async function getFakeCaptcha(
  params: { phoneNum: string },
  options?: { [p: string]: any },
) {
  // return request<API.FakeCaptcha>('/api/login/captcha', {
  return request<API.FakeCaptcha>('/apisvc-auth/api/open/auth/send/sms', {
    method: 'POST',
    params: {
      ...params,
    },
    ...(options || {}),
  });
}
