FROM alpine:3.8

# Add a cherry user to run our application so that it doesn't need to run as root
RUN adduser -D -s /bin/sh cherry
WORKDIR /home/cherry

ADD cherry cherry
RUN chmod 755 cherry && chown cherry:cherry cherry

ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown cherry:cherry entrypoint.sh
USER cherry

ENTRYPOINT ["./entrypoint.sh"]
EXPOSE 1323

