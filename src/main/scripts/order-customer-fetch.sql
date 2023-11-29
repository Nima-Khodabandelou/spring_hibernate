select o1_0.id,
       o1_0.bill_to_address,
       o1_0.bill_to_city,
       o1_0.bill_to_state,
       o1_0.bill_to_zip_code,
       o1_0.created_date,
       c1_0.id,
       c1_0.address,
       c1_0.city,
       c1_0.state,
       c1_0.zip_code,
       c1_0.created_date,
       c1_0.customer_name,
       c1_0.email,
       c1_0.last_modified_date,
       c1_0.phone,
       o1_0.last_modified_date,
       o2_0.id,
       o2_0.approved_by,
       o2_0.created_date,
       o2_0.last_modified_date,
       o1_0.order_status,
       o1_0.shipping_address,
       o1_0.shipping_city,
       o1_0.shipping_state,
       o1_0.shipping_zip_code
from order_header o1_0
         left join
     customer c1_0
     on c1_0.id = o1_0.customer_id
         left join
     order_approval o2_0
     on o1_0.id = o2_0.order_header_id
where o1_0.id = ?;