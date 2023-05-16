/*
 * @Author: ShiShenApr tpvkeas3708@163.com
 * @Date: 2023-04-18 13:55:08
 * @LastEditors: ShiShenApr tpvkeas3708@163.com
 * @LastEditTime: 2023-05-16 16:12:24
 * @FilePath: \blog-web\src\api\sysUser.ts
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import request from '@/utils/request'
import {urls} from '@/utils/cloud'
export function login(data:any){
  return request({
    url:urls.user+'/sys-user/login',
    method: 'post',
    data: data
  })
}