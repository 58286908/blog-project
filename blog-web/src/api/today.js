import request from '@/utils/request'

export function hello(){
  return request({
    url:'/sys-user/test',
    method: 'post'
  })
}