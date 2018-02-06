//TODO: Write single method for GET,PUT,POST,DELETE


  function handleGet(resourceUrl, handler){
    $.ajax({
       type: 'GET',
       url: resourceUrl,
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


  function handlePost(resourceUrl, body, handler){
    $.ajax({
       type: 'POST',
       url: resourceUrl,
       aysnc: true,
       cache: false,
       headers: {
          'Authorization': getJWT(),
          'Content-Type': 'application/json'
       },
       data: body,
       error: function(jqXHR, textStatus, error){
          handler(jqXHR.status, jqXHR.responseJSON);
        },
       success: function (data, textStatus, jqXHR) {
          handler(jqXHR.status, jqXHR.responseJSON);
        }
      });
  }


  function handlePut(resourceUrl, body, handler){
    $.ajax({
       type: 'PUT',
       url: resourceUrl ,
       aysnc: true,
       cache: false,
       headers: {
          'Authorization': getJWT(),
          'Content-Type': 'application/json'
       },
       data: body,
       error: function(jqXHR, textStatus, error){
          handler(jqXHR.status, jqXHR.responseJSON);
        },
       success: function (data, textStatus, jqXHR) {
          handler(jqXHR.status, jqXHR.responseJSON);
        }
      });
  }


  function handleDelete(resourceUrl, handler){
    $.ajax({
       type: 'DELETE',
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
