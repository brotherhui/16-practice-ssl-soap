# 16-practice-ssl-soap
soap, ssl, example, springboot
I provided 2 ways to access soap:
- wsdl path is https://localhost:8443/ws/users.wsdl?wsdl
- request is https://localhost:8443/ws
1. access by https in resttemplate
2. access by client bean which is wsimport from the real wsdl.


# Way1 - access soap by https post method
1. start 2 server
2. access http://localhost:8080/door


# Way2 - 2nd access soap by setting the properties in to environment
1. cd C:\Users\xiaohui.c.liu\Desktop\New-Folder\workspace-axon\
2. Then remove everything start with server.port.ssl from application.yml and restart server. In this way, we can easily access wsdl with http protocal
3. generate client class by wsdl "C:\Program Files\Java\jdk1.8.0_111\bin\wsimport" -keep -s C:\Users\xiaohui.c.liu\Desktop\New-Folder\workspace-axon\16-practice-ssl-soap\client\src\main\java\ -p com.brotherhui.wsimport -verbose http://localhost:8443/ws/users.wsdl?wsdl
4. Call http://localhost:8080/door2

# Token
please refer to https://github.com/brotherhui/11-practice-ssl-resttemplate to generate tokenkey


