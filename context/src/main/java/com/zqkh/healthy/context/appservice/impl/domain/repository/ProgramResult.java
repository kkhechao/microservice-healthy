package com.zqkh.healthy.context.appservice.impl.domain.repository;

import com.jovezhao.nest.ddd.EntityObject;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 方案结果
 *
 * @author 东来
 * @create 2018/6/8 0008
 */
@NoArgsConstructor
@Getter
public class ProgramResult extends EntityObject {

    private String familyMember;

    private String programId;

    private Date startTime;

    private Date endTime;

    private long insistDay;

    private int  wanderNum;

    private int giveUpNum;

    private long finishNum;

    private Date createTime;

    /**
     * 总人数
     */
    private long total=0;

    /**
     * 超越人数
     */
    private long transcend=0;

    /**
     * 方案冗余
     */
    private Program program;


    public void init(String familyMember,String programId,Program program,Date startTime,Date endTime,long insistDay,int wanderNum,int giveUpNum,long finishNum,long total,long transcend){
        this.familyMember=familyMember;
        this.programId=programId;
        this.program=program;
        this.startTime=startTime;
        this.endTime=endTime;
        this.insistDay=insistDay;
        this.wanderNum=wanderNum;
        this.giveUpNum=giveUpNum;
        this.finishNum=finishNum;
        this.createTime=new Date();
        this.total=total;
        this.transcend=transcend;
    }






}
