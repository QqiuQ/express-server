package com.team24.express;

import com.team24.express.entity.Address;
import com.team24.express.entity.User;
import com.team24.express.mapper.UserMapper;
import com.team24.express.service.AddressService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @className: AddressTests
 * @author: Bobby
 * @date: 11/8/2023
 **/
@SpringBootTest
public class AddressTests {
    @Resource
    UserMapper userMapper;
    @Autowired
    AddressService addressService;

    @Test
    void createAddresses() {
        User user = userMapper.selectByUsername("vividbobo");

        for (int i = 0; i < 10; i++) {
            Address address = new Address();
            address.setProvince("福建省");
            address.setCity("福州市");
            address.setCountry("闽侯县");
            address.setStreet("上街镇");
            address.setDetail("福州大学" + i);
            address.setRecipientName("黄黄" + i);
            address.setPhone("188888" + i);
            address.setUserId(user.getId());
            addressService.add(address);
        }
    }

}
