package com.zqkh.healthy.context.appservice.impl.domain.repository;

import com.jovezhao.nest.ddd.EntityObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 方案
 *
 * @author 东来
 * @create 2018/6/5 0005
 */
@NoArgsConstructor
@Getter
public class Program  extends EntityObject {


    private static final String DATE_FORMAT="yyyy-MM-dd";


    public enum Status{

        /**
         * 接收
         */
        RECEIVE,

        /**
         * 拒绝接收时间
         */
        REJECT,
        /**
         * 开启
         */
        OPEN,

        /**
         * 结束
         */
        END,
        ;

        public static final Status getStatus(String status){
            if(ObjectUtils.isEmpty(status)){
                return null;
            }
            for (Status st:Status.values()) {
                if(status.equals(st.name())){
                    return st;
                }
            }

            return null;
        }


    }

    /**
     * 来源类型
     */
    public enum SrcType{
        /**
         * 测试结果
         */
        TEST_PAPER_RESULT
        ;

        public static final SrcType getSrcType(String srcType){
            if(ObjectUtils.isEmpty(srcType)){
                return null;
            }

            for (SrcType src:SrcType.values()) {
                if(srcType.equals(src.name())){
                    return src;
                }
            }

            return null;
        }



    }

    /**
     * 所属成员
     */
    private String familyMember;

    /**
     * 方案状态
     */
    private Status status;

    /**
     * 来源类型
     */
    private SrcType srcType;


    /**
     * 接收时间
     */
    private Date receiveTime;

    /**
     * 拒绝时间
     */
    private Date rejectTime;


    /**
     * 开启时间
     */
    private Date openTime;

    /**
     * 关闭时间
     */
    private Date endTime;


    /**
     * 来源
     */
    private String src;

    /**
     * 来源备注
     */
    private String srcDesc;

    /**
     * 方案模板
     */
    private ProgramTemplate programTemplate;

    /**
     * 方案模板
     */
    private String programTemplateId;


    /**
     * 反馈
     */
    private List<ProgramFeedback> feedback;


    /**
     * 分享图
     */
    private String sharingImg;


    /**
     * 初始化
     * @param familyMember:成员
     * @param srcType:来源类型
     * @param src:来源
     * @param programTemplate:方案模板
     */
    public void init(String familyMember,SrcType srcType,String src,String srcDesc,ProgramTemplate programTemplate,String sharingImg){
        this.familyMember=familyMember;
        this.srcType=srcType;
        this.src=src;
        this.srcDesc=srcDesc;
        this.programTemplate=programTemplate;
        this.programTemplateId=programTemplate.getId().toValue();
        this.sharingImg=sharingImg;
        this.status=Status.RECEIVE;
        this.receiveTime=new Date();
    }

    /**
     * 开启方案
     */
    public void open(){
        this.status=Status.OPEN;
        if(ObjectUtils.isEmpty(openTime)){
            this.openTime=new Date();
        }


    }

    /**
     * 拒绝方案
     */
    public void reject(){
        this.status=Status.REJECT;
        if(ObjectUtils.isEmpty(rejectTime)){
            this.rejectTime=new Date();
        }
    }

    /**
     * 结束方案
     */
    public void end(){
        this.status=Status.END;
        if(ObjectUtils.isEmpty(endTime)){
            this.endTime=new Date();
        }
    }

    /**
     * 反馈
     * @param programFeedback
     */
    public void feedbackMessage(ProgramFeedback programFeedback){
        if(ObjectUtils.isEmpty(this.feedback)){
            this.feedback=new ArrayList<>();
        }
        Iterator<ProgramFeedback> iterator = feedback.iterator();
        while (iterator.hasNext()){
            ProgramFeedback programFeedback1=iterator.next();
            if( DateFormatUtils.format(programFeedback.getTime(),DATE_FORMAT).equals( DateFormatUtils.format(programFeedback1.getTime(),DATE_FORMAT))){
                iterator.remove();
            }
        }
        this.feedback.add(programFeedback);
    }

}
