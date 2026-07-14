export default function ActionBar({

    title,
    buttonLabel,
    onButtonClick,
    searchValue,
    onSearchChange,
    children

}) {

    return (

        <div className="action-bar">

            <div className="action-bar-left">

                {title && <h2>{title}</h2>}

                {children}

            </div>

            <div className="action-bar-right">
                <input
                    type="text"
                    placeholder="Search..."
                    value={searchValue || ""}
                    onChange={(e) => onSearchChange?.(e.target.value)}
                />
                {
                    

                    buttonLabel && (

                        <button
                            className="primary-btn"
                            onClick={onButtonClick}
                        >

                            {buttonLabel}

                        </button>

                    )

                }

            </div>

        </div>

    );

}