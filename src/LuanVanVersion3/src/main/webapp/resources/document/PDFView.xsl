<?xml version="1.0" encoding ="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:fo="http://www.w3.org/1999/XSL/Format"
    version='1.0'>
	<xsl:output xmlns:xslt="http://xml.apache.org/xslt" indent="yes" encoding="UTF-8" omit-xml-declaration="yes"/>
    <xsl:template match="documentText">
        <fo:root>
            <fo:layout-master-set>
                 <fo:simple-page-master master-name="mainPage"
                     page-height="300mm" page-width="200mm"
                     margin-top="20mm" margin-bottom="20mm"
                     margin-left="20mm" margin-right="20mm">

                     <fo:region-body region-name="xsl-region-body"
                       margin-top=".50in" margin-bottom=".5in"/>

                     <fo:region-before region-name="xsl-region-before" extent=".5in"/>
            		<fo:region-after region-name="xsl-region-after" extent=".5in"/>
                 </fo:simple-page-master>
             </fo:layout-master-set>

             <fo:page-sequence master-reference="mainPage">
             	<fo:static-content flow-name="xsl-region-before" font-size="10pt" font-family="Times New Roman">
                       <fo:block>sample header</fo:block>
                </fo:static-content>
        		<fo:static-content flow-name="xsl-region-after" font-size="10pt" >
	            <fo:block>lỗi utf8</fo:block>
	        	</fo:static-content>
                 <fo:flow  flow-name="xsl-region-body">
                     <xsl:apply-templates/>
                 </fo:flow>
             </fo:page-sequence>
        </fo:root>
    </xsl:template>

    <xsl:template match="name">
		<fo:block font-size="32pt" line-height="24pt"
       	 font-weight="bold">
		Đặc Tả Yêu Cầu Phần Mềm            
   		</fo:block>
    </xsl:template>
		
    <xsl:template match="code">
        <fo:block font-size="18pt" line-height="24pt"
            font-family="sans-serif">
            Population (people):
            <xsl:value-of select="."/>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>