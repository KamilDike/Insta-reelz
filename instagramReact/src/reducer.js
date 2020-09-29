export const initialState = {
    category: null,
};

export const actionTypes = {
    SET_CATEGORY: "SET_CATEGORY",
}

const reducer = (state, action) => {
    console.log(action);
    switch (action.type) {
        case actionTypes.SET_CATEGORY:
            return {
                ...state,
                category: action.category,
            };

            default:
                return state;
    }
};

export default reducer;