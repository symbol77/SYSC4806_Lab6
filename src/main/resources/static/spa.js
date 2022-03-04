$(document).on('click','#createBook', function() {
   $.ajax({
      type: "POST",
      url: '/books/newBook',
      data: {
      },
    }).then(function(data) {
        $('.book-id').replaceWith("Address Book Id: " + data.id);
        $('#formAddBuddy').attr("action", "/books/" + data.id + "/addBuddy");
    });
});

$(document).on('submit','#formAddBuddy', function(event) {
    var form = $(this);
    var data = {};
    data['name'] = $('[name="name"]').val();
    data['number'] = $('[name="number"]').val();
    data['address'] = $('[name="address"]').val();

    var url = form.attr('action');

    event.preventDefault();

   $.ajax({
      headers: {
        Accept: "application/json; charset=utf-8",
        "Content-Type": "application/json; charset=utf-8"
      },
      type: "POST",
      url: url,
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: function(response) {
          console.log(response);
          $('#buddies').append('<tr><td>' + response.name + '</td><td>' + response.number + '</td><td>' + response.address +'</td></tr>');
      },
      error: function(xhr, status, error) {
        alert(xhr.responseText);
      }

    });
});