import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import axiosInstance from "../api/axiosInstance";
import { CURRENT_USER } from "../api/config";
import useMessage from "../hooks/messages";
import { setAuthToken, updateUser } from "../store/UserAuthentication/user-authentication-actions";

export default function DataLoader({ children }) {
  const dispatch = useDispatch();
  const [loading, setLoading] = useState(true);
  const { displayError } = useMessage();

  useEffect(() => {
    (async function () {
      setLoading(true);
      const token = localStorage.getItem("token");
      if (token) {
        dispatch(setAuthToken(token));
      }
      try {
        const response = await axiosInstance.get(CURRENT_USER);
        dispatch(updateUser(response));
      } catch (e) {
        if (e.response.status !== 401) {
          displayError("Failed to load user info.");
        }
      } finally {
        setLoading(false);
      }
    })();
  }, [displayError, dispatch]);
  if (loading) {
    return <></>;
  }
  return children;
}
