FROM openjdk:11

RUN curl -LO "https://downloads.wkhtmltopdf.org/0.12/0.12.5/wkhtmltox_0.12.5-1.stretch_amd64.deb" \
  && apt update \
  && apt --fix-broken -y install ./wkhtmltox_0.12.5-1.stretch_amd64.deb \
  && rm wkhtmltox_0.12.5-1.stretch_amd64.deb \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/* \
  && curl -LO https://ipafont.ipa.go.jp/IPAexfont/ipaexm00401.zip  \
  && unzip ipaexm00401.zip \
  && mkdir -p /root/.fonts \
  && mv ipaexm00401/ipaexm.ttf /root/.fonts/ \
  && rm -rf ipaexm00401 ipaexm00401.zip \
  && fc-cache -fv

RUN echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list \
  && curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | apt-key add \
  && apt-get update \
  && apt-get install sbt
