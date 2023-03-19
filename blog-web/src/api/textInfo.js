// import request from '@/utils/request'
import axios from 'axios'
export function save(data) {
    return axios({
        url: '/blog/blog/save',
        method: 'post',
        data: data
    })
}
