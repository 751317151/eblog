package com.blackstar.eblog.schedules;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blackstar.eblog.entity.MPost;
import com.blackstar.eblog.service.MPostService;
import com.blackstar.eblog.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author huah
 * @since 2021年08月29日
 */
@Component
public class ViewCountSyncTask {

  @Autowired
  RedisUtil redisUtil;

  @Autowired
  RedisTemplate redisTemplate;

  @Autowired
  MPostService postService;


  @Scheduled(cron = "0/5 * * * * *") //每分钟同步
  public void task() {

    Set<String> keys = redisTemplate.keys("rank:post:*");

    List<String> ids = new ArrayList<>();
    for (String key : keys) {
      if(redisUtil.hHasKey(key, "post:viewCount")){
        ids.add(key.substring("rank:post:".length()));
      }
    }

    if(ids.isEmpty()) return;

    // 需要更新阅读量
    List<MPost> posts = postService.list(new QueryWrapper<MPost>().in("id", ids));

    posts.stream().forEach((post) ->{
      Integer viewCount = (Integer) redisUtil.hget("rank:post:" + post.getId(), "post:viewCount");
      post.setViewCount(viewCount);
    });

    if(posts.isEmpty()) return;

    boolean isSucc = postService.updateBatchById(posts);

    if(isSucc) {
      ids.stream().forEach((id) -> {
        redisUtil.hdel("rank:post:" + id, "post:viewCount");
        System.out.println(id + "---------------------->同步成功");
      });
    }
  }
}
