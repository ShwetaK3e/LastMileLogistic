package com.shwetak3e.zentello.models;

/**
 * Created by Pervacio on 5/20/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;





public class ShipmentItem{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("shipment")
    @Expose
    private Shipment shipment;
    @SerializedName("bookedItem")
    @Expose
    private BookedItem bookedItem;
    @SerializedName("shippedItemCount")
    @Expose
    private Integer shippedItemCount;
    @SerializedName("loadedCount")
    @Expose
    private Integer loadedCount;
    @SerializedName("unloadedCount")
    @Expose
    private Integer unloadedCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public BookedItem getBookedItem() {
        return bookedItem;
    }

    public void setBookedItem(BookedItem bookedItem) {
        this.bookedItem = bookedItem;
    }

    public Integer getShippedItemCount() {
        return shippedItemCount;
    }

    public void setShippedItemCount(Integer shippedItemCount) {
        this.shippedItemCount = shippedItemCount;
    }

    public Integer getLoadedCount() {
        return loadedCount;
    }

    public void setLoadedCount(Integer loadedCount) {
        this.loadedCount = loadedCount;
    }

    public Integer getUnloadedCount() {
        return unloadedCount;
    }

    public void setUnloadedCount(Integer unloadedCount) {
        this.unloadedCount = unloadedCount;
    }


    public class BookedItem {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("articleType")
        @Expose
        private ArticleType articleType;
        @SerializedName("itemNumber")
        @Expose
        private String itemNumber;
        @SerializedName("commodityName")
        @Expose
        private String commodityName;
        @SerializedName("sizeUOM")
        @Expose
        private String sizeUOM;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("length")
        @Expose
        private Integer length;
        @SerializedName("width")
        @Expose
        private Integer width;
        @SerializedName("height")
        @Expose
        private Integer height;
        @SerializedName("cftVolume")
        @Expose
        private Integer cftVolume;
        @SerializedName("cftWeight")
        @Expose
        private Integer cftWeight;
        @SerializedName("actualWeight")
        @Expose
        private Integer actualWeight;
        @SerializedName("chargedWeight")
        @Expose
        private Integer chargedWeight;
        @SerializedName("unitPrice")
        @Expose
        private Integer unitPrice;
        @SerializedName("totalPrice")
        @Expose
        private Integer totalPrice;
        @SerializedName("itemCount")
        @Expose
        private Integer itemCount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public ArticleType getArticleType() {
            return articleType;
        }

        public void setArticleType(ArticleType articleType) {
            this.articleType = articleType;
        }

        public String getItemNumber() {
            return itemNumber;
        }

        public void setItemNumber(String itemNumber) {
            this.itemNumber = itemNumber;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getSizeUOM() {
            return sizeUOM;
        }

        public void setSizeUOM(String sizeUOM) {
            this.sizeUOM = sizeUOM;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getLength() {
            return length;
        }

        public void setLength(Integer length) {
            this.length = length;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public Integer getCftVolume() {
            return cftVolume;
        }

        public void setCftVolume(Integer cftVolume) {
            this.cftVolume = cftVolume;
        }

        public Integer getCftWeight() {
            return cftWeight;
        }

        public void setCftWeight(Integer cftWeight) {
            this.cftWeight = cftWeight;
        }

        public Integer getActualWeight() {
            return actualWeight;
        }

        public void setActualWeight(Integer actualWeight) {
            this.actualWeight = actualWeight;
        }

        public Integer getChargedWeight() {
            return chargedWeight;
        }

        public void setChargedWeight(Integer chargedWeight) {
            this.chargedWeight = chargedWeight;
        }

        public Integer getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(Integer unitPrice) {
            this.unitPrice = unitPrice;
        }

        public Integer getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(Integer totalPrice) {
            this.totalPrice = totalPrice;
        }

        public Integer getItemCount() {
            return itemCount;
        }

        public void setItemCount(Integer itemCount) {
            this.itemCount = itemCount;
        }


        public class ArticleType {

            @SerializedName("id")
            @Expose
            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

        }

    }

    public class Shipment {

        @SerializedName("id")
        @Expose
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

}

