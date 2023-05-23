

function ucitaj()
{
    // document.getElementById("formaholder").style.display = "none";
  //  document.getElementById("formaholder2").style.display = "none";
    document.getElementById("nakonRegistracije").style.display = "none";
}

// function prikazisignup()
// {
//     document.getElementById("dugmici").style.display = "none";
//     document.getElementById("natpis").style.display = "none";
//     document.getElementById("naslov").style.display = "none";
//     document.getElementById("formaholder").style.display = "flex";
// }
//
// function prikazilogin()
// {
//     document.getElementById("dugmici").style.display = "none";
//     document.getElementById("natpis").style.display = "none";
//     document.getElementById("naslov").style.display = "none";
//     document.getElementById("formaholder2").style.display = "flex";
// }

function nazad()
{
    document.getElementById("formaholder").style.display = "none";
    document.getElementById("formaholder2").style.display = "none";
    document.getElementById("dugmici").style.display = "flex";
    document.getElementById("natpis").style.display = "block";
    document.getElementById("naslov").style.display = "block";
}

function proveraSifri()
{
    sifra1 = document.getElementById("sifra1").value;
    sifra2 = document.getElementById("sifra2").value;

    if(sifra1===sifra2)
        return true;
    return false;
}

function prikaziNakonRegistracije()
{
    var ind = proveraSifri();
    if(ind==true)
    {
        document.getElementById("formaholder").style.display = "none";
        document.getElementById("nakonRegistracije").style.display = "flex";
    }
}
