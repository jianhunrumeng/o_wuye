package com.wuye.common.vo;


import java.io.Serializable;

/**
 * =============================================================================<br>
 * ����˵��: ����������������� ���¼�¼�� ���� ���� ����<br>
 * =============================================================================<br>
 * 2015-07-19 tanyw �������ļ�����ʵ�ֻ���
 * <p/>
 * =============================================================================<br>
 */
public class FillSelectVO extends RetVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1810024319409806227L;

    private String[] ZBFLList;

    public String[] getZBFLList() {
        return ZBFLList;
    }

    public void setZBFLList(String[] list) {
        ZBFLList = list;
    }

}
