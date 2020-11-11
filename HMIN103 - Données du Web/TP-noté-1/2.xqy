for $x in doc("document.xml")/tweeter/tweet/message
return
   <result>
      {$x/hashtag}   
   </result>