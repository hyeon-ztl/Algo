SELECT
  EXTRACT(YEAR  FROM o.sales_date) AS year,
  EXTRACT(MONTH FROM o.sales_date) AS month,
  COUNT(DISTINCT o.user_id)        AS purchased_users,
  ROUND(
    COUNT(DISTINCT o.user_id) /
    (SELECT COUNT(*)
       FROM user_info
      WHERE joined >= DATE '2021-01-01'
        AND joined <  DATE '2022-01-01'
    ), 1
  ) AS purchased_ratio
FROM online_sale o
JOIN user_info u
  ON u.user_id = o.user_id
 AND u.joined >= DATE '2021-01-01'
 AND u.joined <  DATE '2022-01-01'
GROUP BY
  EXTRACT(YEAR  FROM o.sales_date),
  EXTRACT(MONTH FROM o.sales_date)
ORDER BY year, month;
