# SSMParkingSys
基于SSM框架的停车场管理系统

演示视频链接：https://www.bilibili.com/video/BV1AF41137D5/

详询 微信：egvh56ufy7hh QQ：821898835，承接商业项目和毕设

这是一个SSM+Redis的停车场管理系统。

系统有3权限：用户，管理员，超级管理员。

支付：仅有一个假的支付（管的比较严，没有企业认证申请不了）

功能：

停车位管理（管理员，超级管理员）

停车卡管理（用户，管理员，超级管理员）-（临时停车，正常卡，月卡，年卡）-（由于懒的原因，对于收费的设定只建了表，没有实际应用（现在写死在代码中-。-）。有需要的可以修改一下，然后放到redis缓存中即可，效率不减）

优惠券管理（用户，管理员，超级管理员）

违规管理（用户，管理员，超级管理员）

收入管理（超级管理员）

用户管理（用户，管理员，超级管理员）

历史停车（用户，管理员，超级管理员）

导出功能（超级管理员）-（仅有导出收入信息）
