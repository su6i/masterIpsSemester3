doc('document.xml')/ancestor-or-self::author
doc("document.xml")/ancestor-or-self//author[1]
doc("document.xml")/tweeter/*/ancestor-or-self::author[1]
doc("document.xml")/tweeter/*/ancestor-or-self::author[1]
doc("document.xml")//author/ancestor::*[1]
doc("document.xml")/tweet//author

/child::element(title)
tweet//author


for $x in doc("document.xml")//author
return
   <result>
      {$x}   
   </result>



for $b in ddoc("document.xml")/tweeter
where $b/title = "TCP/IP Illustrated"
return
<book>
{ $b/@* }
{ $b/* except $b/price }
</book>

doc('document.xml')/descendant-or-self::element(tweeter)/child::element(author)

for $x in doc("document.xml")//tweet/*     return    $x/name()

for $b in doc("document.xml")/CarParkDataImport/CarPark
where $b/Location = $a
return $b/(Location | CarParkName | Postcode)

for $b in doc("document.xml")/CarParkDataImport/CarPark
where $b/Location = $a
return $b/(Location | CarParkName | Postcode)


doc("document.xml")//tweet ! (author, date)

for $b in doc("document.xml")//tweet
where not(($b/author))
return $b

for $b in doc("document.xml")//tweet
let $new-items := remove($b,/message)
return
   <result>   
      
      <items>
      {
         for $b in $new-items
         return <item>{$b}</item>
      }
      </items>
      
   </result>