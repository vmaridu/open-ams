
  function handleGet(resourceUrl, handler){
    $.ajax({
       type: 'GET',
       url: resourceUrl ,
       aysnc: true,
       cache: false,
       headers: {
          'Authorization': getJWT()
       },
       error: function(jqXHR, textStatus, error){
          handler(jqXHR.status, jqXHR.responseJSON);
        },
       success: function (data, textStatus, jqXHR) {
          handler(jqXHR.status, jqXHR.responseJSON);
        }
      });
  }
