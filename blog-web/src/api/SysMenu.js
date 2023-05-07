import request from '@/utils/request'

import {anonymous} from '@/utils/request'
export function save(data){
  return request({
    url:'/sys-menu/save',
    method: 'post',
    data: data
  })
}

export function listByMenu(){
  return anonymous({
    url:'/sys-menu/listByMenu',
    method: 'get',
  })
}

export function update(data){
  return request({
    url:'/sys-menu/update',
    method:'put',
    data: data
  })
}