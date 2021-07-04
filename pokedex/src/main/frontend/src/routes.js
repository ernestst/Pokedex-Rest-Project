import Login from "./components/Login";
import Registration from "./components/Registration";
import Search from "./components/Search";
import Pokemon from "./components/Pokemon";
import Admin from "./components/Admin";
import Team from "./components/Team";

export const routes = [
    {path: '/login', component: Login},
    {path: '/registration', component: Registration},
    {path: '/search', component: Search, name:'search'},
    {path: '/admin', component: Admin},
    {path: '/team', component: Team},
    {path: '/pokemon/:id', component: Pokemon, name:'pokemon'},
    {path: "*", redirect: "/search"}
]