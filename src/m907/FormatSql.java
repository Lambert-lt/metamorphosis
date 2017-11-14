package m907;

public class FormatSql {
	public static void main(String[] args) {

		String a = "SELECT\n" +
				"	so.deliveryDate,\n" +
				"	ci.id AS clientId,\n" +
				"	ui. NAME AS clientName,\n" +
				"	ui.telephone,\n" +
				"	ui.backupTelephone,\n" +
				"	so.id AS orderId,\n" +
				"	so.orderNumber,\n" +
				"	so.address,\n" +
				"	so.orderStatus,\n" +
				"	CASE\n" +
				"WHEN so.orderStatus = 'unreceived' THEN\n" +
				"	1\n" +
				"WHEN so.orderStatus = 'partialreceived' THEN\n" +
				"	2\n" +
				"WHEN so.orderStatus = 'allreceived' THEN\n" +
				"	3\n" +
				"WHEN so.orderStatus = 'stop' THEN\n" +
				"	4\n" +
				"ELSE\n" +
				"	5\n" +
				"END AS sort,\n" +
				" so.orderType,\n" +
				" 'N'\n" +
				"FROM\n" +
				"	t_simple_purchase_order so\n" +
				"LEFT JOIN t_simple_purchase_order_detail sod ON so.id = sod.purchaseOrder_id\n" +
				"LEFT JOIN t_prod_product p ON p.id = sod.product_id\n" +
				"LEFT JOIN t_prod_ProdSpec ps ON sod.prodSpecId = ps.id\n" +
				"LEFT JOIN t_prod_ProdColour pc ON sod.prodColourId = pc.id,\n" +
				" t_crm_client_info ci,\n" +
				" t_crm_user_info ui\n" +
				"WHERE\n" +
				"	so.ownerId = 342\n" +
				"AND sod.ownerId = 342\n" +
				"AND ui.ownerId = 342\n" +
				"AND ci.ownerId = 342\n" +
				"AND p.ownerId = 342\n" +
				"AND sod.product_id = p.id\n" +
				"AND ci.id = so.clientInfo_id\n" +
				"AND ci.userInfo_id = ui.id\n" +
				"GROUP BY\n" +
				"	so.id\n" +
				"ORDER BY\n" +
				"	so.deliveryDate ASC,\n" +
				"	sort ASC,\n" +
				"	so.orderNumber ASC";
		String b = a.replace("\n", " ");
		b = b.replaceAll("	", " ");
		b = b.replaceAll("  ", " ");
		System.out.println(b);
		
	}
}
