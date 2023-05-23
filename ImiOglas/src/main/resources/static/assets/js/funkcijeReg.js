function prikaziNakonRegistracije()
{
    var ind = proveraSifri();
    if(ind==false)
    {
        document.getElementById("sifra2").value="";
        alert("Sifre se ne podudaraju.");
    }
}

function proveraSifri()
{
    sifra1 = document.getElementById("sifra1").value;
    sifra2 = document.getElementById("sifra2").value;

    if(sifra1===sifra2)
        return true;
    return false;
}

function ucitaj()
{
    document.getElementById("nakonRegistracije").style.display = "none";
}