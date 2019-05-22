package com.demo.cloud.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;


@Data
@Table(name = "QOP_ORDERS")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QopOrders {
    /**
     * corresponds to the database column qop_orders.ORDER_CODE
     */
    private String orderCode;

    /**
     * corresponds to the database column qop_orders.SOURCE_ORDER_ID
     */
    private Long sourceOrderId;

    /**
     * corresponds to the database column qop_orders.PX_ORDER_ID
     */
    private String pxOrderId;

    /**
     * corresponds to the database column qop_orders.SOURCE_PARENT_ORDER_ID
     */
    private Long sourceParentOrderId;

    /**
     * corresponds to the database column qop_orders.PARENT_ORDER_CODE
     */
    private String parentOrderCode;

    /**
     * corresponds to the database column qop_orders.BUYER_ID
     */
    private Integer buyerId;

    /**
     * corresponds to the database column qop_orders.BUYER_NAME
     */
    private String buyerName;

    /**
     * corresponds to the database column qop_orders.BUYER_EMAIL
     */
    private String buyerEmail;

    /**
     * corresponds to the database column qop_orders.BUYER_CELLPHONE
     */
    private String buyerCellphone;

    /**
     * corresponds to the database column qop_orders.REGION_ID
     */
    private Integer regionId;

    /**
     * corresponds to the database column qop_orders.SHIP_REGION
     */
    private String shipRegion;

    /**
     * corresponds to the database column qop_orders.SHIP_ADDRESS
     */
    private String shipAddress;

    /**
     * corresponds to the database column qop_orders.SHIP_ZIPCODE
     */
    private String shipZipcode;

    /**
     * corresponds to the database column qop_orders.SHIP_NAME
     */
    private String shipName;

    /**
     * corresponds to the database column qop_orders.SHIP_TELPHONE
     */
    private String shipTelphone;

    /**
     * corresponds to the database column qop_orders.SHIP_CELLPHONE
     */
    private String shipCellphone;

    /**
     * corresponds to the database column qop_orders.SHIP_EMAIL
     */
    private String shipEmail;

    /**
     * corresponds to the database column qop_orders.SHIPPING_MODE_ID
     */
    private Integer shippingModeId;

    /**
     * corresponds to the database column qop_orders.SHIPPING_MODE_NAME
     */
    private String shippingModeName;

    /**
     * corresponds to the database column qop_orders.REAL_SHIPPING_MODE_ID
     */
    private Integer realShippingModeId;

    /**
     * corresponds to the database column qop_orders.REAL_SHIPPING_MODE_NAME
     */
    private String realShippingModeName;

    /**
     * corresponds to the database column qop_orders.SHIPPER_ID
     */
    private Integer shipperId;

    /**
     * corresponds to the database column qop_orders.SHIPPER_NAME
     */
    private String shipperName;

    /**
     * corresponds to the database column qop_orders.SHIPPER_CELLPHONE
     */
    private String shipperCellphone;

    /**
     * corresponds to the database column qop_orders.FREIGHT
     */
    private BigDecimal freight;

    /**
     * corresponds to the database column qop_orders.FREIGHT_ADJUSTED
     */
    private BigDecimal freightAdjusted;

    /**
     * corresponds to the database column qop_orders.FREIGHT_ACTUAL
     */
    private BigDecimal freightActual;

    /**
     * corresponds to the database column qop_orders.WEIGHT
     */
    private Integer weight;

    /**
     * corresponds to the database column qop_orders.SHIPPING_STATUS
     */
    private Short shippingStatus;

    /**
     * corresponds to the database column qop_orders.SHIP_ORDER_NUMBER
     */
    private String shipOrderNumber;

    /**
     * corresponds to the database column qop_orders.EXPRESS_COMPANY_NAME
     */
    private String expressCompanyName;

    /**
     * corresponds to the database column qop_orders.EXPRESS_COMPANY_ABB
     */
    private String expressCompanyAbb;

    /**
     * corresponds to the database column qop_orders.PAYMENT_TYPE_ID
     */
    private Integer paymentTypeId;

    /**
     * corresponds to the database column qop_orders.PAYMENT_TYPE_NAME
     */
    private String paymentTypeName;

    /**
     * corresponds to the database column qop_orders.PAYMENT_GATEWAY
     */
    private String paymentGateway;

    /**
     * corresponds to the database column qop_orders.PAYMENT_STATUS
     */
    private Short paymentStatus;

    /**
     * corresponds to the database column qop_orders.REFUND_STATUS
     */
    private Short refundStatus;

    /**
     * corresponds to the database column qop_orders.PAY_CURRENCY_CODE
     */
    private String payCurrencyCode;

    /**
     * corresponds to the database column qop_orders.PAY_CURRENCY_NAME
     */
    private String payCurrencyName;

    /**
     * corresponds to the database column qop_orders.PAYMENT_FEE
     */
    private BigDecimal paymentFee;

    /**
     * corresponds to the database column qop_orders.PAYMENT_FEE_ADJUSTED
     */
    private BigDecimal paymentFeeAdjusted;

    /**
     * corresponds to the database column qop_orders.GATEWAY_ORDER_ID
     */
    private String gatewayOrderId;

    /**
     * corresponds to the database column qop_orders.ORDER_TOTAL
     */
    private BigDecimal orderTotal;

    /**
     * corresponds to the database column qop_orders.ORDER_POINT
     */
    private Integer orderPoint;

    /**
     * corresponds to the database column qop_orders.ORDER_COST_PRICE
     */
    private BigDecimal orderCostPrice;

    /**
     * corresponds to the database column qop_orders.ORDER_PROFIT
     */
    private BigDecimal orderProfit;

    /**
     * corresponds to the database column qop_orders.ORDER_OTHER_COST
     */
    private BigDecimal orderOtherCost;

    /**
     * corresponds to the database column qop_orders.ORDER_OPTION_PRICE
     */
    private BigDecimal orderOptionPrice;

    /**
     * corresponds to the database column qop_orders.DISCOUNT_NAME
     */
    private String discountName;

    /**
     * corresponds to the database column qop_orders.DISCOUNT_AMOUNT
     */
    private BigDecimal discountAmount;

    /**
     * corresponds to the database column qop_orders.DISCOUNT_ADJUSTED
     */
    private BigDecimal discountAdjusted;

    /**
     * corresponds to the database column qop_orders.DISCOUNT_VALUE
     */
    private BigDecimal discountValue;

    /**
     * corresponds to the database column qop_orders.DISCOUNT_VALUE_TYPE
     */
    private Short discountValueType;

    /**
     * corresponds to the database column qop_orders.COUPON_CODE
     */
    private String couponCode;

    /**
     * corresponds to the database column qop_orders.COUPON_NAME
     */
    private String couponName;

    /**
     * corresponds to the database column qop_orders.COUPON_AMOUNT
     */
    private BigDecimal couponAmount;

    /**
     * corresponds to the database column qop_orders.COUPON_VALUE
     */
    private BigDecimal couponValue;

    /**
     * corresponds to the database column qop_orders.COUPON_VALUE_TYPE
     */
    private Short couponValueType;

    /**
     * corresponds to the database column qop_orders.ACTIVITY_NAME
     */
    private String activityName;

    /**
     * corresponds to the database column qop_orders.ACTIVITY_FREE_AMOUNT
     */
    private BigDecimal activityFreeAmount;

    /**
     * corresponds to the database column qop_orders.ACTIVITY_STATUS
     */
    private Short activityStatus;

    /**
     * corresponds to the database column qop_orders.GROUPBUY_ID
     */
    private Integer groupbuyId;

    /**
     * corresponds to the database column qop_orders.GROUPBUY_PRICE
     */
    private BigDecimal groupbuyPrice;

    /**
     * corresponds to the database column qop_orders.GROUPBUY_STATUS
     */
    private Short groupbuyStatus;

    /**
     * corresponds to the database column qop_orders.AMOUNT
     */
    private BigDecimal amount;

    /**
     * corresponds to the database column qop_orders.ORDER_TYPE
     */
    private Short orderType;

    /**
     * corresponds to the database column qop_orders.ORDER_STATUS
     */
    private Short orderStatus;

    /**
     * corresponds to the database column qop_orders.SELLER_ID
     */
    private Integer sellerId;

    /**
     * corresponds to the database column qop_orders.SELLER_NAME
     */
    private String sellerName;

    /**
     * corresponds to the database column qop_orders.SELLER_EMAIL
     */
    private String sellerEmail;

    /**
     * corresponds to the database column qop_orders.SELLER_CELLPHONE
     */
    private String sellerCellphone;

    /**
     * corresponds to the database column qop_orders.SUPPLIER_ID
     */
    private Integer supplierId;

    /**
     * corresponds to the database column qop_orders.SUPPLIER_NAME
     */
    private String supplierName;

    /**
     * corresponds to the database column qop_orders.REFER_ID
     */
    private String referId;

    /**
     * corresponds to the database column qop_orders.REFER_URL
     */
    private String referUrl;

    /**
     * corresponds to the database column qop_orders.ORDER_IP
     */
    private String orderIp;

    /**
     * corresponds to the database column qop_orders.REMARK
     */
    private String remark;

    /**
     * corresponds to the database column qop_orders.COMMENT_STATUS
     */
    private Short commentStatus;

    /**
     * corresponds to the database column qop_orders.PRODUCT_TOTAL
     */
    private BigDecimal productTotal;

    /**
     * corresponds to the database column qop_orders.HAS_CHILDREN
     */
    private Boolean hasChildren;

    /**
     * corresponds to the database column qop_orders.IS_REVIEWS
     */
    private Boolean isReviews;

    /**
     * corresponds to the database column qop_orders.CREATE_USER_ID
     */
    private Integer createUserId;

    /**
     * corresponds to the database column qop_orders.REFER_TYPE
     */
    private Integer referType;

    /**
     * corresponds to the database column qop_orders.ORIGINAL_ID
     */
    private Long originalId;

    /**
     * corresponds to the database column qop_orders.ORDER_TYPE_SUB
     */
    private Short orderTypeSub;

    /**
     * corresponds to the database column qop_orders.SUBACCOUNTID
     */
    private Integer subaccountid;

    /**
     * corresponds to the database column qop_orders.SUB_ACCOUNT_USER_NAME
     */
    private String subAccountUserName;

    /**
     * corresponds to the database column qop_orders.SUB_ACCOUNT_NAME
     */
    private String subAccountName;

    /**
     * corresponds to the database column qop_orders.SHIP_ACCOUNT
     */
    private String shipAccount;

    /**
     * corresponds to the database column qop_orders.ERROR_COUNT
     */
    private Integer errorCount;

    /**
     * corresponds to the database column qop_orders.STOCK_CODE
     */
    private String stockCode;

    /**
     * corresponds to the database column qop_orders.DATA_SOURCE
     */
    private String dataSource;

    /**
     * corresponds to the database column qop_orders.ORDER_PROMOTION_TYPE
     */
    private Integer orderPromotionType;

    /**
     * corresponds to the database column qop_orders.PAY_END_DATE
     */
    private Date payEndDate;

    /**
     * corresponds to the database column qop_orders.SOLDTO_CODE
     */
    private String soldtoCode;

    /**
     * corresponds to the database column qop_orders.SOLDTO_NAME
     */
    private String soldtoName;

    /**
     * corresponds to the database column qop_orders.SHIPTO_CODE
     */
    private String shiptoCode;

    /**
     * corresponds to the database column qop_orders.SHIPTO_NAME
     */
    private String shiptoName;

    /**
     * corresponds to the database column qop_orders.IS_EMERGENCY
     */
    private Integer isEmergency;

    /**
     * corresponds to the database column qop_orders.BATCH_ID
     */
    private String batchId;

    /**
     * corresponds to the database column qop_orders.DEPARTMENT_CODE
     */
    private String departmentCode;

    /**
     * corresponds to the database column qop_orders.GROUPBUY_CODE
     */
    private String groupbuyCode;

    /**
     * corresponds to the database column qop_orders.CREATED_DATE
     */
    private Date createdDate;

    /**
     * corresponds to the database column qop_orders.UPDATED_DATE
     */
    private Date updatedDate;

    /**
     * corresponds to the database column qop_orders.APPROVE_STATUS
     */
    private Integer approveStatus;

    /**
     * corresponds to the database column qop_orders.SHIP_COMPANY_CODE
     */
    private String shipCompanyCode;

    /**
     * corresponds to the database column qop_orders.SHIP_PROVINCE_CODE
     */
    private String shipProvinceCode;

    /**
     * corresponds to the database column qop_orders.SHIP_CITY_CODE
     */
    private String shipCityCode;

    /**
     * corresponds to the database column qop_orders.SHIP_COUNTY_CODE
     */
    private String shipCountyCode;

    /**
     * corresponds to the database column qop_orders.SHIP_TOWN_CODE
     */
    private String shipTownCode;

    /**
     * corresponds to the database column qop_orders.SHIP_REMARK
     */
    private String shipRemark;

    /**
     * corresponds to the database column qop_orders.RECIPIENTS_NAME
     */
    private String recipientsName;

    /**
     * corresponds to the database column qop_orders.RECIPIENTS_PHONE
     */
    private String recipientsPhone;

    /**
     * corresponds to the database column qop_orders.RECIPIENTS_CELLPHONE
     */
    private String recipientsCellphone;

    /**
     * corresponds to the database column qop_orders.ORDER_ERROR_CODE
     */
    private String orderErrorCode;

    /**
     * corresponds to the database column qop_orders.ORDER_ERROR_DESC
     */
    private String orderErrorDesc;

    /**
     * corresponds to the database column qop_orders.ENTERPRISE_ID
     */
    private Integer enterpriseId;

    /**
     * corresponds to the database column qop_orders.ENTERPRISE_CODE
     */
    private String enterpriseCode;

    /**
     * corresponds to the database column qop_orders.ENTERPRISE_NAME
     */
    private String enterpriseName;

    /**
     * corresponds to the database column qop_orders.CUSTOMER_ID
     */
    private Integer customerId;

    /**
     * corresponds to the database column qop_orders.CUSTOMER_CODE
     */
    private String customerCode;

    /**
     * corresponds to the database column qop_orders.TOTAL_PRICE
     */
    private BigDecimal totalPrice;

    /**
     * corresponds to the database column qop_orders.TOTAL_TAX_PRICE
     */
    private BigDecimal totalTaxPrice;

    /**
     * corresponds to the database column qop_orders.TOTAL_NAKED_PRICE
     */
    private BigDecimal totalNakedPrice;

    /**
     * corresponds to the database column qop_orders.SYNC_DELIVERY_NO
     */
    private String syncDeliveryNo;

    /**
     * corresponds to the database column qop_orders.SYNC_DELIVERY_TYPE
     */
    private String syncDeliveryType;

    /**
     * corresponds to the database column qop_orders.SYNC_DELIVERY_TIME
     */
    private String syncDeliveryTime;

    /**
     * corresponds to the database column qop_orders.SYNC_DELIVERY_STATUS
     */
    private String syncDeliveryStatus;

    /**
     * corresponds to the database column qop_orders.SYNC_RECEIVE_STATUS
     */
    private String syncReceiveStatus;

    /**
     * corresponds to the database column qop_orders.SYNC_RECIPIENTS
     */
    private String syncRecipients;

    /**
     * corresponds to the database column qop_orders.SYNC_RECIPTIME
     */
    private String syncReciptime;

    /**
     * corresponds to the database column qop_orders.SYNC_RECEIVE_TIME
     */
    private String syncReceiveTime;

    /**
     * corresponds to the database column qop_orders.SUPPLIER_CODE
     */
    private String supplierCode;

    /**
     * corresponds to the database column qop_orders.API_LOG_ID
     */
    private String apiLogId;

    /**
     * corresponds to the database column qop_orders.IS_PREORDER
     */
    private String isPreorder;

    /**
     * corresponds to the database column qop_orders.PREORDERACTION
     */
    private String preorderaction;

    /**
     * corresponds to the database column qop_orders.PX_DELIVERY_NO
     */
    private String pxDeliveryNo;

    /**
     * corresponds to the database column qop_orders.SALES_ORG_CODE
     */
    private String salesOrgCode;

    /**
     * corresponds to the database column qop_orders.FACTORY_CODE
     */
    private String factoryCode;

    /**
     * corresponds to the database column qop_orders.COMPOSITE_ADVANCED_AMOUNT
     */
    private BigDecimal compositeAdvancedAmount;

    /**
     * corresponds to the database column qop_orders.COMPOSITE_ONLINE_AMOUNT
     */
    private BigDecimal compositeOnlineAmount;

    /**
     * corresponds to the database column qop_orders.DELIVERY_AMOUNT
     */
    private BigDecimal deliveryAmount;

    /**
     * corresponds to the database column qop_orders.CANCEL_AMOUNT
     */
    private BigDecimal cancelAmount;

    /**
     * corresponds to the database column qop_orders.FINISH_AMOUNT
     */
    private BigDecimal finishAmount;

    /**
     * corresponds to the database column qop_orders.TO_OMS_STATUS
     */
    private String toOmsStatus;

    /**
     * corresponds to the database column qop_orders.SYNC_TRANSACTION_STATUS
     */
    private String syncTransactionStatus;

    /**
     * corresponds to the database column qop_orders.DISTRIBUTOR_CODE
     */
    private String distributorCode;

    /**
     * corresponds to the database column qop_orders.SCORE
     */
    private Integer score;

    /**
     * corresponds to the database column qop_orders.SCORE_AMOUNT
     */
    private BigDecimal scoreAmount;

    /**
     * corresponds to the database column qop_orders.DISTRIBUTOR_NAME
     */
    private String distributorName;

    /**
     * corresponds to the database column qop_orders.CREATE_PO_STATUS
     */
    private String createPoStatus;

    /**
     * corresponds to the database column qop_orders.CREATE_PO_RESULT
     */
    private String createPoResult;

    /**
     * corresponds to the database column qop_orders.THIRD_PARTY_ORDER_CODE
     */
    private String thirdPartyOrderCode;

    /**
     * corresponds to the database column qop_orders.SELLER_MEMO
     */
    private String sellerMemo;

    /**
     * corresponds to the database column qop_orders.BUYER_MEMO
     */
    private String buyerMemo;

    /**
     * corresponds to the database column qop_orders.DEPARTMENT_NAME
     */
    private String departmentName;

    /**
     * corresponds to the database column qop_orders.PX_PO_CODE
     */
    private String pxPoCode;

    /**
     * corresponds to the database column qop_orders.PX_ERP_CODE
     */
    private String pxErpCode;

    /**
     * corresponds to the database column qop_orders.PX_ORDER_STATUS
     */
    private String pxOrderStatus;

    /**
     * corresponds to the database column qop_orders.SHIPPER_ADDRESS
     */
    private String shipperAddress;


}