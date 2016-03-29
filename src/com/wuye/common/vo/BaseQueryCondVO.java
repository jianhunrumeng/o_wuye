package com.wuye.common.vo;

/**
 * @���ࣺBaseQueryCond
 * @author: tanyw
 * @version: V1.0
 * @see:
 * @�������ڣ�Jan 15, 2015
 * @����˵����
 * @�޸ļ�¼�� =============================================================<br>
 * ����:Jan 15, 2015 Administrator ���� ����������ѯ������
 * =============================================================<br>
 */
public class BaseQueryCondVO {

    private static final int DEFAUL_PAGE_SIZE = 10;// Ĭ�ϵ�ҳ���С
    private static final int DEFAUL_CUR_PAGE = 1; // Ĭ�ϵĵ�ǰҳ

    public int curPage = DEFAUL_CUR_PAGE; // ��ǰҳ
    public int pageSize = DEFAUL_PAGE_SIZE; // ÿҳ��¼��

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
