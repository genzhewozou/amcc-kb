
# IVR 相关接口

## 获取匹配的最佳三个专业

```$xslt
请求：
curl -X POST \
  http://192.168.50.191:8006/api/v1/IVR/professionTop3 \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'Host: 192.168.50.191:8006' \
  -d 'provinceName=%E9%99%95%E8%A5%BF%E7%9C%81&score=400&classCategory=%E7%90%86%E5%B7%A5&mobilePhone=18992073212&tenantId=f295e6c5-1672-4515-a2a9-88b6c93370fe'

返回：
会计学,网络与新媒体,土木工程
```

参数说明：
- provinceName 省份 必填 如：陕西省/陕西
- score 分数 必填 如：450
- classCategory 科类 必填 文史、理工、艺术（文）、艺术（理）、体育（文）、体育（理）、单招
- mobilePhone 主叫号码 必填
- tenantId 租户ID 必填

返回说明：
200 返回字符串

异常说明：
非200
{
  "error": "NOT_MATCH_PROFESSION",
  "code": 4009,
  "description": "Not match Profession"
}



## 生成短信中的链接加密串
  
```$xslt
请求：
curl -X POST \
  http://192.168.50.191:8006/api/v1/IVR/generateKey \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'Host: 192.168.50.191:8006' \
  -d 'provinceName=%E9%99%95%E8%A5%BF%E7%9C%81&score=450&classCategory=%E6%96%87%E5%8F%B2&mobilePhone=1839239032&tenantId=f295e6c5-1672-4515-a2a9-88b6c93370fe' 

返回：
XXXXXXXX
```  

参数说明：
- provinceName 省份 必填 如：陕西省/陕西
- score 分数 必填 如：450
- classCategory 科类 必填 文史、理工、艺术（文）、艺术（理）、体育（文）、体育（理）、单招
- mobilePhone 主叫号码 必填
- tenantId 租户ID 必填

返回说明：
200 返回字符串

异常说明：
非200
{
  "error": "NOT_MATCH_PROFESSION",
  "code": 4009,
  "description": "Not match Profession"
}