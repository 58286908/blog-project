/*
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-04-21 15:52:15
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-05-16 16:10:12
 * @FilePath: \blog-web\src\api\SysMenu.ts
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import request from '@/utils/request'
import {urls} from '@/utils/cloud'
import {anonymous} from '@/utils/request'
export function save(data:any){
  return request({
    url:urls.blog+'/sys-menu/save',
    method: 'post',
    data: data
  })
}

export function listByMenu(){
  return anonymous({
    url:urls.blog+'/sys-menu/listByMenu',
    method: 'get',
  })
}

export function update(data:any){
  return request({
    url:urls.blog+'/sys-menu/update',
    method:'put',
    data: data
  })
}