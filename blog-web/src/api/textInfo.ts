/*
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-03-20 02:10:02
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-05-12 20:09:53
 * @FilePath: \blog-web\src\api\textInfo.js
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import request from '@/utils/request'
import {anonymous} from '@/utils/request'

export function save(data:any){
  return request({
    url:'/blog-textInfo/save',
    method: 'post',
    data: data
  })
}

export function update(data:any){
  return request({
    url:'/blog-textInfo/update',
    method: 'put',
    data: data 
  })
}

export function getById(id:any){
  return anonymous({
    url:'/blog-textInfo/id/'+id,
  })
}

export function list(data:any){
  return anonymous({
    url:'/blog-textInfo/list',
    method: 'post',
    headers: {
      'Content-Type': 'text/plain'
    },
    data: data
  }) 
}
