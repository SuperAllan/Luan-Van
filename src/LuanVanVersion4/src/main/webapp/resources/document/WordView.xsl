<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
  version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 
  <xsl:output method="xml" indent="yes"/>
  <xsl:template match="/">
 
    <xsl:processing-instruction name="mso-application">
      <xsl:text>progid="Word.Document"</xsl:text>
    </xsl:processing-instruction>
    <w:wordDocument xmlns:w="http://schemas.microsoft.com/office/word/2003/wordml">
      <w:body>
        <xsl:for-each select="documentText">
        <w:p>
          <w:r>
            <w:t><xsl:value-of select="code"/></w:t>
          </w:r>
          <w:r>
            <w:t><xsl:value-of select="name"/></w:t>
          </w:r>
          </w:p>
        </xsl:for-each>
      </w:body>
    </w:wordDocument>
  </xsl:template>
</xsl:stylesheet>