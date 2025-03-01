package top.lh.contentservice.page;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_share")
public class Share {
    private Integer id;
    private Integer userId;
    private String title;
    private Date createTime;
    private Date updateTime;
    private Integer isOriginal;
    private String author;
    private String cover;
    private String summary;
    private Integer price;
    private String downloadUrl;
    private Integer buyCount;
    private Integer showFlag;
    private String auditStatus;
    private String reason;
}
