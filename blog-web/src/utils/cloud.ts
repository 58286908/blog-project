const isCloud:boolean = true

export const urls = isCloud?{
  user:'user',
  blog:'blog'
}:{
  user:'',
  blog:''
}