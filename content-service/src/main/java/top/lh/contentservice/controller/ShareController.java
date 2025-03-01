package top.lh.contentservice.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lh.contentservice.client.UserClient;
import top.lh.contentservice.mapper.ShareMapper;
import top.lh.contentservice.page.Share;
import top.lh.contentservice.page.User;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/share")
public class ShareController {
    @Autowired
    private ShareMapper shareMapper;

    @Autowired
    private UserClient userClient;

    @GetMapping("/{id}")
    public Map<String, Object> getShareById(@PathVariable Integer id) {
        Share share = shareMapper.selectById(id);
        User author = userClient.getUserById(share.getUserId());

        Map<String, Object> result = new HashMap<>();
        result.put("share", share);
        result.put("author", author);
        return result;
    }
}
