/**
 * �ļ�����ContractDAO.java
 * ��������������ʵ���dao��Data Access Object���ӿڣ�������Ҫ���󷽷�������ɾ��ġ�
 * �������ڣ�2014-04-24
 * �����ߣ�������
 */

package dao;

import java.util.ArrayList;

import model.*;

public interface IDAO {
	
	/**
	 * ����һ��ʵ�嵽���ݿ�
	 * @param entity IEntity ������Ķ���
	 * @return succ boolean ����ִ�еĽ��
	 */
	public boolean AddEntity(IEntity entity);
	
	/**
	 * �����ݿ�ɾ��һ��ʵ��
	 * @param entity IEntity ��ɾ���Ķ���
	 * @return succ boolean ����ִ�еĽ��
	 */
	public boolean DeleteEntity(IEntity entity);
	
	/**
	 * �����ݿ����һ��ʵ��
	 * @param entity IEntity �µĵĶ����滻��ԭ���Ķ���
	 * @return	succ Boolean ����ִ�еĽ��
	 */
	public boolean UpdateEntity(IEntity entity);
	
	/**
	 * ����һ��ʵ�����
	 * @param id int �����ҵĶ����id
	 * @return entity IEntity ����ҵ�������ָ���Ķ�����û�У�����null
	 */
	public IEntity GetOneEntity(int id);
	
	/**
	 * ����һ��ʵ�����
	 * @param entity IEntity ��������
	 * @return entity IEntity ����ҵ�������ָ���Ķ�����û�У�����null
	 */
	public IEntity GetOneEntity(IEntity entity);
	
	/**
	 * ��ѯָ�������¶��󼯺�
	 * @param entity ��ѯ����
	 * @return arr ArrayList<IEntity> ��ѯ�Ľ����
	 */
	public ArrayList<IEntity> GetEntitySet(IEntity entity);
	
	/**
	 * ��ҳ��ʾʱ����ѯָ��ҳ�Ķ��󼯺�
	 * @param entity ��ѯ����
	 * @param pageNo ָ����ʾ��ҳ��
	 * @param pageRecordNum ָ��ÿҳ��ʾ�ļ�¼��
	 * @return arr ArrayList<IEntity> ��ѯ�Ľ����
	 */
	//public ArrayList<IEntity> GetEntitySet(IEntity entity, int pageNo, int pageRecordNum);

	/**
	 * ���ز�ѯ�����µ�����������
	 * @return int ��ѯ�����µ�����������
	 */
	public int getRecordNum();
	
	/**
	 * ���ز�ѯ�����µ�����ҳ��
	 * @return int ��ѯ�����µ�����ҳ��
	 */
//	public int GetPageNum();
}
