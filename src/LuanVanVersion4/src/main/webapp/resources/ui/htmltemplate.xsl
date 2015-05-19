<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml" indent="yes" encoding="UTF-8" 
        omit-xml-declaration="yes" xslt:indent-amount="4"
        xmlns:xslt="http://xml.apache.org/xslt" />
    <xsl:strip-space elements="*" />
    
    <xsl:template match="/">
    	<html>
    	<head>
    		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
    	</head>
        <body>
            <xsl:apply-templates />
        </body>
        </html>
    </xsl:template>
    
    <xsl:template match="cells">
        <xsl:if test="current()/parent = ''">
        	<xsl:if test="type = 'ui.Div'">
        	    <div>
        	    	<xsl:attribute name="class"><xsl:value-of select="normalize-space(current()/nameClass)" /></xsl:attribute>
        	    	<xsl:for-each select="current()/embeds">
        	    		<xsl:call-template name="trackingChild">
				            <xsl:with-param name="embeds" select="current()" />
				        </xsl:call-template>
        	    	</xsl:for-each>
                </div>
        	</xsl:if>
        	<xsl:if test="type = 'ui.Form'">
        	    <form>
        	    	<xsl:attribute name="style">background:<xsl:value-of select="current()/attrs/rect/fill" /></xsl:attribute>
        	    	<xsl:attribute name="class"><xsl:value-of select="normalize-space(current()/nameClass)" /></xsl:attribute>
        	    	<xsl:for-each select="current()/embeds">
        	    		<xsl:call-template name="trackingChild">
				            <xsl:with-param name="embeds" select="current()" />
				        </xsl:call-template>
        	    	</xsl:for-each>
                </form>
        	</xsl:if>
        	<xsl:if test="type = 'ui.Label'">
        	    <label>
                    <xsl:attribute name="class"><xsl:value-of select="normalize-space(current()/nameClass)" /></xsl:attribute>
                    <xsl:value-of select="current()/name" />
                </label>
        	</xsl:if>
            <xsl:if test="type = 'ui.Button'">
        	    <button>
                    <xsl:attribute name="class"><xsl:value-of select="normalize-space(current()/nameClass)" /></xsl:attribute>
                    <xsl:value-of select="current()/name" />
                </button>
        	</xsl:if>
        	<xsl:if test="type = 'ui.TextBox'">
        	    <input>
                    <xsl:attribute name="class"><xsl:value-of select="normalize-space(current()/nameClass)" /></xsl:attribute>
                    <xsl:attribute name="value"><xsl:value-of select="current()/name" /></xsl:attribute>
                </input>
        	</xsl:if>
        	<xsl:if test="type = 'ui.TextArea'">
        	    <textarea>
                    <xsl:attribute name="class"><xsl:value-of select="normalize-space(current()/nameClass)" /></xsl:attribute>
                    <xsl:value-of select="current()/name" />
                </textarea>
        	</xsl:if>
			<xsl:if test="type = 'ui.Radio'">
        		<div class='radio'>
	        	    <label>
	        	    	<input type='radio'/>
	                    <xsl:value-of select="current()/name" />
	                </label>
	            </div>
        	</xsl:if>
        	<xsl:if test="type = 'ui.RadioChecked'">
        		<div class='radio'>
	        	    <label>
	        	    	<input type='radio' checked='checked'/>
	                    <xsl:value-of select="current()/name" />
	                </label>
	            </div>
        	</xsl:if>
        	<xsl:if test="type = 'ui.Checkbox'">
        		<div class='checkbox'>
	        	    <label>
	        	    	<input type='checkbox'/>
	                    <xsl:value-of select="current()/name" />
	                </label>
	            </div>
        	</xsl:if>
        	<xsl:if test="type = 'ui.CheckboxChecked'">
        		<div class='checkbox'>
	        	    <label>
	        	    	<input type='checkbox' checked='checked'/>
	                    <xsl:value-of select="current()/name" />
	                </label>
	            </div>
        	</xsl:if>
        	<xsl:if test="type = 'ui.SelectBox'">
        		<select>
        			<xsl:attribute name="class"><xsl:value-of select="normalize-space(current()/nameClass)" /></xsl:attribute>
	        	    <option>
	                    <xsl:value-of select="current()/name" />
	                </option>
	            </select>
        	</xsl:if>
        	<xsl:if test="type = 'ui.Table'">
       			<table>
        			<xsl:attribute name="class"><xsl:value-of select="normalize-space(current()/nameClass)" /></xsl:attribute>
        			<thead>
	        			<tr>
		        			<th>TT</th>
		        			<th>Name</th>
		        			<th>Telephone</th>
	        			</tr>
        			</thead>
        			<tbody>
	        			<tr>
	        				<td>1</td>
	        				<td>Jackson</td>
	        				<td>0913 231 232</td>
	        			</tr>
	        			<tr>
	        				<td>1</td>
	        				<td>Nguyen Van A</td>
	        				<td>01628 89 89 89</td>
	        			</tr>
        			</tbody>
	            </table>
        	</xsl:if>
        </xsl:if>
    </xsl:template>
    
    <xsl:template name="trackingChild">
        <xsl:param name="embeds" />
        <xsl:variable name="childId" select="normalize-space($embeds)" />
        
        <xsl:if test="string-length($childId) > 0">
            <xsl:for-each select="//cells">
                <xsl:variable name="currentId" select="current()/id" />
                <xsl:if test="string($currentId) = string($childId)">
                    <xsl:if test="type = 'ui.Div'">
			       	    <div>
			       	    	<xsl:attribute name="style">background:<xsl:value-of select="current()/attrs/rect/fill" /></xsl:attribute>
			       	    	<xsl:attribute name="class"><xsl:value-of select="current()/nameClass" /></xsl:attribute>
			       	    	<xsl:for-each select="current()/embeds">
			       	    		<xsl:call-template name="trackingChild">
						            <xsl:with-param name="embeds" select="current()" />
						        </xsl:call-template>
			       	    	</xsl:for-each>
			               </div>
			       	</xsl:if>
			       	<xsl:if test="type = 'ui.Form'">
		        	    <form>
		        	    	<xsl:attribute name="style">background:<xsl:value-of select="current()/attrs/rect/fill" /></xsl:attribute>
		        	    	<xsl:attribute name="class"><xsl:value-of select="current()/nameClass" /></xsl:attribute>
		        	    	<xsl:for-each select="current()/embeds">
		        	    		<xsl:call-template name="trackingChild">
						            <xsl:with-param name="embeds" select="current()" />
						        </xsl:call-template>
		        	    	</xsl:for-each>
		                </form>
		        	</xsl:if>
			       	<xsl:if test="type = 'ui.Label'">
			        	    <label>
			                    <xsl:attribute name="class"><xsl:value-of select="normalize-space(current()/nameClass)" /></xsl:attribute>
			                    <xsl:value-of select="current()/name" />
			                </label>
			       	</xsl:if>
			           <xsl:if test="type = 'ui.Button'">
		        	    <button>
		                    <xsl:attribute name="class"><xsl:value-of select="normalize-space(current()/nameClass)" /></xsl:attribute>
		                    <xsl:value-of select="current()/name" />
		                </button>
			       	</xsl:if>
			       	<xsl:if test="type = 'ui.TextBox'">
		        	    <input>
		                    <xsl:attribute name="class"><xsl:value-of select="normalize-space(current()/nameClass)" /></xsl:attribute>
		                    <xsl:attribute name="value"><xsl:value-of select="current()/name" /></xsl:attribute>
		                </input>
			       	</xsl:if>
			       	<xsl:if test="type = 'ui.TextArea'">
		        	    <textarea>
		                    <xsl:attribute name="class"><xsl:value-of select="normalize-space(current()/nameClass)" /></xsl:attribute>
		                    <xsl:value-of select="current()/name" />
		                </textarea>
			       	</xsl:if>
					<xsl:if test="type = 'ui.Radio'">
			       		<div class='radio'>
			        	    <label>
			        	    	<input type='radio'/>
			                    <xsl:value-of select="current()/name" />
			                </label>
			            </div>
			       	</xsl:if>
			       	<xsl:if test="type = 'ui.RadioChecked'">
			       		<div class='radio'>
			        	    <label>
			        	    	<input type='radio' checked='checked'/>
			                    <xsl:value-of select="current()/name" />
			                </label>
			            </div>
			       	</xsl:if>
			       	<xsl:if test="type = 'ui.Checkbox'">
			       		<div class='checkbox'>
			        	    <label>
			        	    	<input type='checkbox'/>
			                    <xsl:value-of select="current()/name" />
			                </label>
			            </div>
			       	</xsl:if>
			       	<xsl:if test="type = 'ui.CheckboxChecked'">
			       		<div class='checkbox'>
			        	    <label>
			        	    	<input type='checkbox' checked='checked'/>
			                    <xsl:value-of select="current()/name" />
			                </label>
			            </div>
			       	</xsl:if>
			       	<xsl:if test="type = 'ui.SelectBox'">
		        		<select>
		        			<xsl:attribute name="class"><xsl:value-of select="normalize-space(current()/nameClass)" /></xsl:attribute>
			        	    <option>
			                    <xsl:value-of select="current()/name" />
			                </option>
			            </select>
		        	</xsl:if>
		        	<xsl:if test="type = 'ui.Table'">
		        		<table>
		        			<xsl:attribute name="class"><xsl:value-of select="normalize-space(current()/nameClass)" /></xsl:attribute>
		        			<tr>
			        			<th>TT</th>
			        			<th>Name</th>
			        			<th>Telephone</th>
		        			</tr>
		        			<tr>
		        				<td>1</td>
		        				<td>Jackson</td>
		        				<td>0913 231 232</td>
		        			</tr>
		        			<tr>
		        				<td>1</td>
		        				<td>Nguyen Van A</td>
		        				<td>01628 89 89 89</td>
		        			</tr>
			            </table>
		        	</xsl:if>
                </xsl:if>
            </xsl:for-each>
        </xsl:if>
    </xsl:template>
    
    <!-- ******* XSLT output formating: removing line breaks, bland output lines 
        n something like that ******* -->
    <xsl:template match="*/text()[normalize-space()]">
        <xsl:value-of select="normalize-space()" />
    </xsl:template>

    <xsl:template match="*/text()[not(normalize-space())]" />
</xsl:stylesheet>