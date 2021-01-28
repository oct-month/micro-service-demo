# coding: utf-8
from thrift.transport import TSocket, TTransport
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer

import smtplib
from email.mime.text import MIMEText
from email.header import Header

from message.api import MessageService


class MessageServiceHandle(MessageService.Iface):
    def __init__(self):
        self.emailSender = "ablocker@163.com"
        self.emailAuthCode = "ZKPPRFZCHCOEYDRS"

    def sendMobileMessage(self, mobile, message):
        print(f"sendMobileMessage, mobile:{mobile}, message:{message}.")
        return True

    def sendEmailMessage(self, email, message):
        print(f"sendEmailMessage, email:{email}, message:{message}.")
        messageObj = MIMEText(message, "plain", "utf-8")
        messageObj['From'] = self.emailSender
        messageObj['To'] = email
        messageObj['Subject'] = Header("信息服务", "utf-8")
        try:
            smtpObj = smtplib.SMTP("smtp.163.com")
            smtpObj.login(self.emailSender, self.emailAuthCode)
            smtpObj.sendmail(self.emailSender, [email, ], messageObj.as_string())
            print("send email success.")
            return True
        except smtplib.SMTPException as e:
            print("send email failed!")
            print(e)
            return False


if __name__ == "__main__":
    handler = MessageServiceHandle()
    processor = MessageService.Processor(handler)

    transport = TSocket.TServerSocket("127.0.0.1", 6060)
    tfactory = TTransport.TFramedTransportFactory()
    pfactory = TBinaryProtocol.TBinaryProtocolFactory()

    server = TServer.TSimpleServer(processor, transport, tfactory, pfactory)

    print("python thrift server start")
    server.serve()
    print("python thrift server exit")
