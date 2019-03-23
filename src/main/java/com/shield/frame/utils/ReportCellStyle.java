package com.shield.frame.utils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ReportCellStyle {

    private HSSFCellStyle styleTopLeft;

    private HSSFCellStyle styleLeft;

    private HSSFCellStyle styleBottomLeft;

    private HSSFCellStyle styleTop;

    private HSSFCellStyle styleMiddle;

    private HSSFCellStyle styleBottom;

    private HSSFCellStyle styleTopRight;

    private HSSFCellStyle styleRight;

    private HSSFCellStyle styleBottomRight;

    private HSSFCellStyle title;//标题

    private HSSFCellStyle subTitle;//副标题

    private HSSFCellStyle middle;//居中

    private HSSFCellStyle middleBottom;//居中底加宽

    private HSSFCellStyle styleContent;

    private HSSFCellStyle styleTop2;

    private HSSFCellStyle styleFunds;

    private HSSFCellStyle styleNumber;//数字格式

    public ReportCellStyle(HSSFWorkbook wb) {
        title = wb.createCellStyle();
        title.setFont(PoiUtil.getHSSFFont(wb));
        title.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        subTitle = wb.createCellStyle();
        subTitle.setFont(PoiUtil.getHSSFFontBySize(wb, 10));
        subTitle.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        middle = wb.createCellStyle();
        middle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        middle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        middle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        middle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        middle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        middle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        middleBottom = wb.createCellStyle();
        middleBottom.setBorderTop(HSSFCellStyle.BORDER_THIN);
        middleBottom.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        middleBottom.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        middleBottom.setBorderRight(HSSFCellStyle.BORDER_THIN);
        middleBottom.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        middleBottom.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        styleTopLeft = wb.createCellStyle();
        //styleTopLeft.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        // styleTopLeft.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleTopLeft.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        styleTopLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleTopLeft.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        styleTopLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleTopLeft.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleTopLeft.setFont(PoiUtil.getHSSFFont(wb));

        styleLeft = wb.createCellStyle();
        styleLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleLeft.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        styleLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleLeft.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleLeft.setFont(PoiUtil.getHSSFFont(wb));

        styleBottomLeft = wb.createCellStyle();
        styleBottomLeft.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        styleBottomLeft.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        styleBottomLeft.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
        styleBottomLeft.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        styleBottomLeft.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleBottomLeft.setFont(PoiUtil.getHSSFFont(wb));

        styleTop = wb.createCellStyle();
        styleTop.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        styleTop.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleTop.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        styleTop.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleTop.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleTop.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleTop.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleTop.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 14); //字体大小
        font.setFontName("宋体");
        font.setBoldweight(font.BOLDWEIGHT_BOLD); //粗体
        styleTop.setFont(font);

        styleMiddle = wb.createCellStyle();
        styleMiddle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleMiddle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleMiddle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleMiddle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleMiddle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        styleMiddle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        styleBottom = wb.createCellStyle();
        styleBottom.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleBottom.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        styleBottom.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleBottom.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleBottom.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        styleBottom.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        styleTopRight = wb.createCellStyle();
        styleTopRight.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        styleTopRight.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleTopRight.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        styleTopRight.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleTopRight.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleTopRight.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        styleTopRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

        styleRight = wb.createCellStyle();
        styleRight.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleRight.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleRight.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleRight.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        styleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        styleRight.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        styleBottomRight = wb.createCellStyle();
        styleBottomRight.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleBottomRight.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
        styleBottomRight.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleBottomRight.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
        styleBottomRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        styleBottomRight.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        styleContent = wb.createCellStyle();
        styleContent.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleContent.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleContent.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleContent.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleContent.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        styleContent.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        styleFunds = wb.createCellStyle();
        styleFunds.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleFunds.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleFunds.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleFunds.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleFunds.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        styleFunds.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        styleTop2 = wb.createCellStyle();
        styleTop2.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        styleTop2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleTop2.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
        styleTop2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleTop2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleTop2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleTop2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleTop2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        //2位小数的数字
        HSSFDataFormat df = wb.createDataFormat();
        styleNumber = wb.createCellStyle();
        styleNumber.setDataFormat(df.getFormat("0.00"));
        styleNumber.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleNumber.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleNumber.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleNumber.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleNumber.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        styleNumber.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    }

    public HSSFCellStyle getStyleTopLeft() {
        return styleTopLeft;
    }

    public void setStyleTopLeft(HSSFCellStyle styleTopLeft) {
        this.styleTopLeft = styleTopLeft;
    }

    public HSSFCellStyle getStyleLeft() {
        return styleLeft;
    }

    public void setStyleLeft(HSSFCellStyle styleLeft) {
        this.styleLeft = styleLeft;
    }

    public HSSFCellStyle getStyleBottomLeft() {
        return styleBottomLeft;
    }

    public void setStyleBottomLeft(HSSFCellStyle styleBottomLeft) {
        this.styleBottomLeft = styleBottomLeft;
    }

    public HSSFCellStyle getStyleTop() {
        return styleTop;
    }

    public void setStyleTop(HSSFCellStyle styleTop) {
        this.styleTop = styleTop;
    }

    public HSSFCellStyle getStyleMiddle() {
        return styleMiddle;
    }

    public void setStyleMiddle(HSSFCellStyle styleMiddle) {
        this.styleMiddle = styleMiddle;
    }

    public HSSFCellStyle getStyleBottom() {
        return styleBottom;
    }

    public void setStyleBottom(HSSFCellStyle styleBottom) {
        this.styleBottom = styleBottom;
    }

    public HSSFCellStyle getStyleTopRight() {
        return styleTopRight;
    }

    public void setStyleTopRight(HSSFCellStyle styleTopRight) {
        this.styleTopRight = styleTopRight;
    }

    public HSSFCellStyle getStyleRight() {
        return styleRight;
    }

    public void setStyleRight(HSSFCellStyle styleRight) {
        this.styleRight = styleRight;
    }

    public HSSFCellStyle getStyleBottomRight() {
        return styleBottomRight;
    }

    public void setStyleBottomRight(HSSFCellStyle styleBottomRight) {
        this.styleBottomRight = styleBottomRight;
    }

    public HSSFCellStyle getTitle() {
        return title;
    }

    public void setTitle(HSSFCellStyle title) {
        this.title = title;
    }

    public HSSFCellStyle getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(HSSFCellStyle subTitle) {
        this.subTitle = subTitle;
    }

    public HSSFCellStyle getMiddle() {
        return middle;
    }

    public void setMiddle(HSSFCellStyle middle) {
        this.middle = middle;
    }

    public HSSFCellStyle getMiddleBottom() {
        return middleBottom;
    }

    public void setMiddleBottom(HSSFCellStyle middleBottom) {
        this.middleBottom = middleBottom;
    }

    public HSSFCellStyle getStyleContent() {
        return styleContent;
    }

    public void setStyleContent(HSSFCellStyle styleContent) {
        this.styleContent = styleContent;
    }

    public HSSFCellStyle getStyleTop2() {
        return styleTop2;
    }

    public void setStyleTop2(HSSFCellStyle styleTop2) {
        this.styleTop2 = styleTop2;
    }

    public HSSFCellStyle getStyleFunds() {
        return styleFunds;
    }

    public void setStyleFunds(HSSFCellStyle styleFunds) {
        this.styleFunds = styleFunds;
    }

    /**
     * @return the styleNumber
     */
    public HSSFCellStyle getStyleNumber() {
        return styleNumber;
    }

    /**
     * @param styleNumber the styleNumber to set
     */
    public void setStyleNumber(HSSFCellStyle styleNumber) {
        this.styleNumber = styleNumber;
    }

}
