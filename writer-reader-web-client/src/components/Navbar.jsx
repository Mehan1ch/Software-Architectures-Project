import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import Menu from "@mui/material/Menu";
import MenuIcon from "@mui/icons-material/Menu";
import Container from "@mui/material/Container";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import Tooltip from "@mui/material/Tooltip";
import MenuItem from "@mui/material/MenuItem";
import SvgIcon from "@mui/material/SvgIcon";
import { Link as RouterLink } from "react-router-dom";
import { Divider } from "@mui/material";
import { useAuthContext } from "../contexts/ContextProvider";
import axios from "../api/axios";
import Cookies from "js-cookie";

function LogoSvgIcon() {
  return (
    <SvgIcon sx={{ display: { xs: "none", md: "flex" }, mr: 1 }}>
      <svg
        xmlns="http://www.w3.org/2000/svg"
        width={10}
        height={10}
        viewBox="3.563 5.596 24.029 20.218"
      >
        <path
          stroke="#000"
          fill="#ffffff"
          d="M21.531 7.625c-.062-.591.201-.958.469-1.438-.372.017-.86.182-1.218.29-.796.239-1.571.542-2.188 1.119-.301.282-.533.614-.744.966-.182.305-.414.834-.444 1.188-.136-.326-.086-.864.045-1.188l.267-.531c-.364.104-.993.506-1.313.728-1.383.959-2.344 2.225-2.344 3.96-.299-.577-.238-1.042-.157-1.656-.229.117-.506.528-.646.75-.536.852-.584 1.901-.656 2.875l.021.344c-.224-.51-.25-.987-.25-1.531 0-.228-.01-.333.032-.563-.137.076-.207.21-.286.344l-.378.719c-.293.637-.523 1.419-.524 2.125v.469c.001.344.117 1.095.25 1.406.129-.126.292-.428.389-.594l.457-.719c.363-.563 1.063-1.462 1.507-1.968 1.671-1.909 3.483-3.348 5.679-4.607.954-.546 2.114-1.107 3.156-1.456l-.969.513c-.852.481-1.69.96-2.5 1.51-2.09 1.42-3.964 3.173-5.48 5.195-.431.574-1.211 1.718-1.52 2.344.652.086 1.411.146 2.063.049a7 7 0 0 0 .906-.197l.594-.196-.531-.181c-.476-.221-.678-.528-.844-1.006.685.817 1.821.865 2.75.486.24-.099.538-.285.75-.437.195-.139.641-.479.75-.674-.906-.001-1.685-.511-2.219-1.218.701.141 1.486.53 2.156.531h.375c.949-.002 1.8-.656 2.389-1.343.189-.221.575-.651.674-.906a3.63 3.63 0 0 1-1.906.111l-.938-.267c.213-.061.951-.031 1.218-.032l.563-.053c.939-.122 1.636-.433 2.264-1.165.151-.177.34-.376.424-.594l-.938.122c-.234.013-.832-.095-1.063-.17l-.5-.171.656.032a4.8 4.8 0 0 0 1.688-.272c.867-.316 1.542-.913 2.133-1.603.888-1.036 1.487-2.114 1.992-3.375-.626-.083-1.244-.094-1.875-.094-1.01 0-2.103.035-2.969.626-.332.227-.502.409-.75.718-.15.187-.385.469-.469.688ZM10.406 21.25c.157-.046.271-.121.406-.209.588-.379.969-.856 1.325-1.447.069-.113.319-.522.341-.625.035-.169-.176-.324-.291-.418-.358-.295-.546-.344-1-.402-.083-.004-.259-.041-.331 0-.091.038-.172.402-.201.507-.123.45-.281 1.166-.281 1.625v.5zm9.344-1.5.531.147c.184.066.492.242.656.354.858.586 1.586 1.782 1.562 2.843-.009.382-.104.527-.281.844l.875-.38 1.906-.861c.487-.224.944-.379 1.247-.854.306-.478.302-1.126.149-1.656-.296-1.032-1.226-2.061-2.365-2.063h-.125c-.399.004-1.052.325-1.438.487zm-8.094 1.218.875-.219 2.028-.559.691.029c-.359.186-.947.366-1.343.482l-1.656.472-.781.206-.313.07-.5.229-1.718.5-2.094.666c.597-.461 1.443-.803 2.156-1.042l1-.302-.125-.875c-.293.014-.877.203-1.188.285l-2.469.666c-.922.257-1.692.481-2.656.299.075.264.269.569.441.781.631.777 1.278.988 2.216 1.175l2.531.464 3.875.714 1.968.349 1 .174c-.191-.178-.242-.157-.494-.438-.351-.391-.598-.94-.599-1.468l-1.313.506-.656-.037 1.343-.625.597-.272.112-.322.229-.469c-.508.102-1.983.687-2.531.906-.414.166-.929.406-1.375.406h-.344l.875-.416 1.781-.758 1.188-.421.5-.181.375-.123.281-.152c.23-.117.524-.181.781-.196.255-.015.611.089.844.19.934.402 1.644 1.476 1.656 2.495.005.449-.065.836-.316 1.218l-.341.406 1.031-.459 1.593-.716c.13-.057.725-.336.803-.404.094-.081.204-.276.254-.391.213-.491.138-1.169-.051-1.656-.425-1.099-1.462-2.077-2.693-2.063-.527.006-.997.266-1.468.469l.219-.344-.844.299-1.406.523-1.968.662-2.438.839-1.156.409c-.464.173-.936.36-1.438.362 1.764-.982 3.848-1.546 5.75-2.209l2.125-.785 1.343-.475c-.149-.076-.364-.081-.531-.102l-.906-.125-1.468-.208c-.231-.009-.437.07-.656.133l-.875.236-.625.165c-.079.019-.277.063-.337.099-.086.051-.215.272-.288.366zm4.063 3.281a1.2 1.2 0 0 1 .229-.438c.091-.11.208-.215.334-.284.568-.313 1.177.071 1.391.628a1.17 1.17 0 0 1-.076.969 1.2 1.2 0 0 1-.441.436 1.5 1.5 0 0 1-.688.221c.078.031.104.03.188.032.268.004.459.006.719-.079.586-.192.962-.714.969-1.327v-.313c-.005-.409-.221-.954-.461-1.281-.343-.466-.913-.841-1.508-.841-.776 0-1.366.601-1.375 1.373-.004.407-.008.655.187 1.031.239.464.678.841 1.22.844.377.002.718-.128.825-.531a.65.65 0 0 0-.637-.808c-.341.007-.604.191-.875.371Z"
          strokeWidth={0.031}
        />
      </svg>
    </SvgIcon>
  );
}

function NavBar() {
  const [anchorElNav, setAnchorElNav] = React.useState(null);
  const [anchorElUser, setAnchorElUser] = React.useState(null);
  const { user, setUser, updateUser } = useAuthContext();

  const handleOpenNavMenu = (event) => {
    setAnchorElNav(event.currentTarget);
  };
  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  React.useEffect(() => {
    if (!user) {
      updateUser();
    }
  }, []);

  const getXSRFCookie = () => {
    return Cookies.get("XSRF-TOKEN");
  };

  const handleLogout = async () => {
    try {
      const response = await axios.post("/logout", null, {
        headers: {
          "X-XSRF-TOKEN": getXSRFCookie(),
        },
      });
      console.log(JSON.stringify(response));
      if (response.status === 204) {
        setUser(null);
      }
    } catch (error) {
      console.log(error);
    }
    handleCloseUserMenu();
  };

  return (
    <AppBar position="static">
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          <LogoSvgIcon />
          <Typography
            variant="h6"
            noWrap
            component="a"
            sx={{
              mr: 2,
              display: { xs: "none", md: "flex" },
              fontFamily: "monospace",
              fontWeight: 700,
              letterSpacing: ".3rem",
              color: "inherit",
              textDecoration: "none",
            }}
          >
            Író-Olvasó
          </Typography>
          <Box sx={{ flexGrow: 1, display: { xs: "flex", md: "none" } }}>
            <IconButton
              size="large"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleOpenNavMenu}
              color="inherit"
            >
              <MenuIcon />
            </IconButton>
            <Menu
              id="menu-appbar"
              anchorEl={anchorElNav}
              anchorOrigin={{
                vertical: "bottom",
                horizontal: "left",
              }}
              keepMounted
              transformOrigin={{
                vertical: "top",
                horizontal: "left",
              }}
              open={Boolean(anchorElNav)}
              onClose={handleCloseNavMenu}
              sx={{ display: { xs: "block", md: "none" } }}
            >
              <MenuItem
                key="Works"
                onClick={handleCloseNavMenu}
                component={RouterLink}
                to="/works"
              >
                <Typography sx={{ textAlign: "center" }}>Művek</Typography>
              </MenuItem>
              <Divider />
              <MenuItem
                key="Collections"
                onClick={handleCloseNavMenu}
                component={RouterLink}
                to="/collections"
              >
                <Typography sx={{ textAlign: "center" }}>
                  Gyűjtemények
                </Typography>
              </MenuItem>
            </Menu>
          </Box>
          <Typography
            variant="h5"
            noWrap
            component="a"
            sx={{
              mr: 2,
              display: { xs: "flex", md: "none" },
              flexGrow: 1,
              fontFamily: "monospace",
              fontWeight: 700,
              letterSpacing: ".3rem",
              color: "inherit",
              textDecoration: "none",
            }}
          >
            Író-Olvasó
          </Typography>
          <Box sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}>
            <Button
              key="Works"
              onClick={handleCloseNavMenu}
              component={RouterLink}
              to="/works"
              sx={{
                my: 2,
                color: "white",
                display: "block",
                fontWeight: "bold",
              }}
            >
              Művek
            </Button>
            <Button
              key="Collections"
              onClick={handleCloseNavMenu}
              component={RouterLink}
              to="/collections"
              sx={{
                my: 2,
                color: "white",
                display: "block",
                fontWeight: "bold",
              }}
            >
              Gyűjtemények
            </Button>
          </Box>
          {user && (
            <Box sx={{ flexGrow: 0 }}>
              <Tooltip title={user?.name}>
                <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                  <Avatar alt={user?.name} src="/static/images/avatar/2.jpg" />
                </IconButton>
              </Tooltip>
              <Menu
                sx={{ mt: "45px" }}
                id="menu-appbar"
                anchorEl={anchorElUser}
                anchorOrigin={{
                  vertical: "top",
                  horizontal: "right",
                }}
                keepMounted
                transformOrigin={{
                  vertical: "top",
                  horizontal: "right",
                }}
                open={Boolean(anchorElUser)}
                onClose={handleCloseUserMenu}
              >
                <MenuItem
                  key="profile"
                  onClick={handleCloseUserMenu}
                  component={RouterLink}
                  to="/profile"
                >
                  <Typography sx={{ textAlign: "center" }}>
                    Saját profilom
                  </Typography>
                </MenuItem>
                <MenuItem
                  key="account"
                  onClick={handleCloseUserMenu}
                  component={RouterLink}
                  to="/account"
                >
                  <Typography sx={{ textAlign: "center" }}>
                    Fiókbeállítások
                  </Typography>
                </MenuItem>
                <MenuItem key="logout" onClick={handleLogout}>
                  <Typography sx={{ textAlign: "center" }}>
                    Kijelentkezés
                  </Typography>
                </MenuItem>
              </Menu>
            </Box>
          )}
        </Toolbar>
      </Container>
    </AppBar>
  );
}
export default NavBar;
