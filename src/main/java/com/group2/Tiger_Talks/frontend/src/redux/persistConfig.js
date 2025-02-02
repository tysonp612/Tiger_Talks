import storage from "redux-persist/lib/storage";

const persistConfig = {
	key: "root",
	storage,
	whitelist: ["user", "globalGroups", "globalUsers"],
};

export default persistConfig;
