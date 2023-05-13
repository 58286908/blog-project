import request from '@/utils/request'

export function login(data:any){
  return request({
    url:'/sys-user/login',
    method: 'post',
    data: data
  })
}