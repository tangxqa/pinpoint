/*
 * Copyright 2018 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.navercorp.pinpoint.web.alarm;

import com.navercorp.pinpoint.web.alarm.checker.AlarmChecker;
import com.navercorp.pinpoint.web.service.UserGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author minwoo.jung
 */
public class EmptySmsSender implements SmsSender {

    @Autowired
    private UserGroupService userGroupService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void sendSms(AlarmChecker checker, int sequenceCount) {
        List<String> receivers = userGroupService.selectPhoneNumberOfMember(checker.getuserGroupId());

        if (receivers.size() == 0) {
            return;
        }


        for (String receiver : receivers) {
            StringBuilder builder = new StringBuilder();
            for (String msg : checker.getSmsMessage()) {
                builder.append(msg);
                builder.append(";");
            }
            if (!SmsUtils.sendVerificationCodeForLogin(receiver, builder.toString())) {
                logger.error("Send sms error for {}", receiver);

            }
        }


    }
}
