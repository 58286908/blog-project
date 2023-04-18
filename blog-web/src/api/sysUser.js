import request from '@/utils/request'

export function login(data){
  return request({
    url:'/api/sys-user/login',
    method: 'post',
    data: data
  })
}