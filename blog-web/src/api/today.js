import request from '@/utils/request'

export function hello(){
  return request({
    url:'/api/sys-user/test',
    method: 'post'
  })
}