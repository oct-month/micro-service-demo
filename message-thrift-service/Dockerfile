FROM python:3.8
LABEL maintainer="sun oct-month@qq.com"

WORKDIR /message-service
COPY ./ /message-service/
RUN pip install -i https://pypi.tuna.tsinghua.edu.cn/simple -r requirements.txt 

ENTRYPOINT [ "python3", "message-service.py" ]
